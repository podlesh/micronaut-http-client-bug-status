package io.micronaut.bug;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.client.exceptions.ReadTimeoutException;
import io.reactivex.Flowable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "httpclient", description = "...",
        mixinStandardHelpOptions = true)
public class HttpclientCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "print message before each attempted code")
    boolean verbose;
    @Option(names = {"-S", "--sync"}, description = "synchronous variant: try one code after another")
    boolean synchronous;
    @Option(names = {"-F", "--fast-fail"}, description = "first error in flow cancels that flow")
    boolean fastFail;
    @Option(names = {"-w", "--wait"}, description = "wait period at the end (in seconds)", defaultValue = "10")
    int endWaitSeconds;

    @Parameters(arity = "1..*", descriptionKey = "status-code")
    public List<Integer> codes;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(HttpclientCommand.class, args);
    }

    //--------------------------------------------------------------------------------

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public static final String BASE_URL = "https://httpstat.us/";

    @Client(BASE_URL)
    @Inject
    protected RxHttpClient httpClient;


    public void run() {
        if (codes == null) {
            return;
        }

        if (synchronous) {
            synchronousTest();
        }
        else {
            pipelineTest();
        }

        if (endWaitSeconds > 0) {
            System.out.println("waiting for " + endWaitSeconds + "s for any timeout");
            try {
                Thread.sleep(1000L * endWaitSeconds);
            }
            catch (InterruptedException e) {
                //ok, end
            }
        }
    }

    private void pipelineTest() {
        try {
            Flowable.fromIterable(codes)
                    .flatMap(code -> {
                                Flowable<Result> resultFlow = httpClient.exchange("" + code, String.class)
                                        .map(success -> new Result(code, success));
                                if (!fastFail) {
                                    resultFlow = resultFlow.onErrorReturn(error -> new Result(code, error));
                                }
                                return resultFlow;
                            }
                    )
                    .blockingForEach(Result::print);
        }
        catch (RuntimeException e) {
            if (fastFail) {
                System.out.println("pipeline failed with exception: " + e);
            }
        }
    }

    private class Result {
        private final int code;
        private final Throwable error;
        private final HttpResponse<String> success;

        public Result(int code, Throwable error) {
            this.code = code;
            this.error = error;
            this.success = null;
        }

        public Result(int code, HttpResponse<String> success) {
            this.code = code;
            this.success = success;
            this.error = null;
        }

        public void print() {
            if (success != null) {
                System.out.printf("OK: response to %s%s: %s%n", BASE_URL, code, success.getStatus());
            }
            else if (error instanceof HttpClientResponseException) {
                final HttpClientResponseException e = (HttpClientResponseException) this.error;
                System.out.printf("OK: response to %s%s: %s / %s%n", BASE_URL, code, e.getStatus(), e.getMessage());
            }
            else if (error instanceof ReadTimeoutException) {
                System.out.printf("BUG! request to %s%s failed with timeout!%n", BASE_URL, code);
                LOGGER.error("completely wrong exception", error);
            }
            else if (error instanceof IllegalArgumentException && error.getMessage().startsWith("Invalid HTTP status code:")) {
                System.out.printf("OK-ish: request to %s%s failed with exception %s%n", BASE_URL, code, error);
            }
            else if (error instanceof Exception) {
                System.out.printf("OK-ish: request to %s%s failed with exception %s%n", BASE_URL, code, error);
                LOGGER.info("unexpected exception", error);
            }
            else {
                LOGGER.warn("fatal error", error);
            }
        }

    }

    private void synchronousTest() {
        try {
            for (Integer code : codes) {
                if (verbose) {
                    System.out.printf("Trying http code %d%n", code);
                }
                Result result;
                try {
                    final HttpResponse<String> response = httpClient.exchange("" + code, String.class)
                            .firstOrError()
                            .blockingGet();
                    result = new Result(code, response);
                }
                catch (Exception e) {
                    result = new Result(code, e);
                }
                result.print();
            }
        }
        finally {
            try {
                System.out.flush();
            }
            catch (Exception ignored) {
                //ignore
            }
        }
    }
}
