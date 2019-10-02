package io.micronaut.bug;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientException;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.client.exceptions.ReadTimeoutException;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "httpclient", description = "...",
        mixinStandardHelpOptions = true)
public class HttpclientCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "prign message before each attempted code")
    boolean verbose;

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
        try {
            for (Integer code : codes) {
                if (verbose) {
                    System.out.printf("Trying http code %d%n", code);
                }
                try {
                    final HttpResponse<String> response = httpClient.exchange("" + code, String.class)
                            .firstOrError()
                            .blockingGet();
                    System.out.printf("OK: response to %s%s: %s%n", BASE_URL, code, response.getStatus());
                }
                catch (HttpClientResponseException e) {
                    System.out.printf("OK: response to %s%s: %s / %s%n", BASE_URL, code, e.getStatus(), e.getMessage());
                }
                catch (ReadTimeoutException e) {
                    System.out.printf("BUG! request to %s%s failed with timeout!%n", BASE_URL, code);
                    LOGGER.error("completely wrong exception", e);
                }
                catch (Exception e) {
                    System.out.printf("OK-ish: request to %s%s failed with exception %s%n", BASE_URL, code, e);
                    LOGGER.info("unexpected exception", e);
                }
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
