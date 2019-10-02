

1. Build:
    ```bash
    ./gradlew build
    ```
2. Check that https://httpstat.us/ is running and available:
    ```bash
    java -jar build/libs/httpclient-0.1-all.jar 200 400 409 500
    ``` 
    Expected output:
    ```
    10:50:33.203 [main] INFO  i.m.context.env.DefaultEnvironment - Established active environments: [cli]
    OK: response to https://httpstat.us/200: OK
    OK: response to https://httpstat.us/400: BAD_REQUEST / Bad Request
    OK: response to https://httpstat.us/409: CONFLICT / Conflict
    OK: response to https://httpstat.us/500: INTERNAL_SERVER_ERROR / Internal Server Error
    ```
3. Try it with some non-standard code:
    ```bash
    java -jar build/libs/httpclient-0.1-all.jar 519
    ``` 
    Expected output (well, not really expected, but at least somewhat acceptable):
    ```
    10:50:33.203 [main] INFO  i.m.context.env.DefaultEnvironment - Established active environments: [cli]
    OK-ish: request to https://httpstat.us/519 failed with exception java.lang.IllegalArgumentException: Invalid HTTP status code: 519
    ```
    Real output:
    ``` 
    10:51:12.109 [main] INFO  i.m.context.env.DefaultEnvironment - Established active environments: [cli]
    10:51:14.519 [nioEventLoopGroup-1-2] WARN  i.n.channel.DefaultChannelPipeline - An exceptionCaught() event was fired, and it reached at the tail of the pipeline. It usually means the last handler in the pipeline did not handle the exception.
    java.lang.IllegalArgumentException: Invalid HTTP status code: 519
        at io.micronaut.http.HttpStatus.valueOf(HttpStatus.java:146)
        at io.micronaut.http.client.FullNettyClientHttpResponse.<init>(FullNettyClientHttpResponse.java:82)
        at io.micronaut.http.client.DefaultHttpClient$10.channelRead0(DefaultHttpClient.java:1867)
        at io.micronaut.http.client.DefaultHttpClient$10.channelRead0(DefaultHttpClient.java:1761)
        at io.netty.channel.SimpleChannelInboundHandler.channelRead(SimpleChannelInboundHandler.java:105)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
        at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:352)
        at io.netty.handler.codec.MessageToMessageDecoder.channelRead(MessageToMessageDecoder.java:102)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
        at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:352)
        at io.netty.handler.codec.MessageToMessageDecoder.channelRead(MessageToMessageDecoder.java:102)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
        at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:352)
        at io.netty.channel.CombinedChannelDuplexHandler$DelegatingChannelHandlerContext.fireChannelRead(CombinedChannelDuplexHandler.java:438)
        at io.netty.handler.codec.ByteToMessageDecoder.fireChannelRead(ByteToMessageDecoder.java:328)
        at io.netty.handler.codec.ByteToMessageDecoder.channelRead(ByteToMessageDecoder.java:302)
        at io.netty.channel.CombinedChannelDuplexHandler.channelRead(CombinedChannelDuplexHandler.java:253)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
        at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:352)
        at io.netty.handler.timeout.IdleStateHandler.channelRead(IdleStateHandler.java:287)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
        at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:352)
        at io.netty.handler.ssl.SslHandler.unwrap(SslHandler.java:1475)
        at io.netty.handler.ssl.SslHandler.decodeJdkCompatible(SslHandler.java:1224)
        at io.netty.handler.ssl.SslHandler.decode(SslHandler.java:1271)
        at io.netty.handler.codec.ByteToMessageDecoder.decodeRemovalReentryProtection(ByteToMessageDecoder.java:505)
        at io.netty.handler.codec.ByteToMessageDecoder.callDecode(ByteToMessageDecoder.java:444)
        at io.netty.handler.codec.ByteToMessageDecoder.channelRead(ByteToMessageDecoder.java:283)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
        at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:352)
        at io.netty.channel.DefaultChannelPipeline$HeadContext.channelRead(DefaultChannelPipeline.java:1422)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:374)
        at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:360)
        at io.netty.channel.DefaultChannelPipeline.fireChannelRead(DefaultChannelPipeline.java:931)
        at io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe.read(AbstractNioByteChannel.java:163)
        at io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:700)
        at io.netty.channel.nio.NioEventLoop.processSelectedKeysOptimized(NioEventLoop.java:635)
        at io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:552)
        at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:514)
        at io.netty.util.concurrent.SingleThreadEventExecutor$6.run(SingleThreadEventExecutor.java:1044)
        at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
        at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
        at java.lang.Thread.run(Thread.java:748)
    BUG! request to https://httpstat.us/519 failed with timeout!
    10:51:19.546 [main] ERROR io.micronaut.bug.HttpclientCommand - completely wrong exception
    io.micronaut.http.client.exceptions.ReadTimeoutException: Read Timeout
        at io.micronaut.http.client.exceptions.ReadTimeoutException.<clinit>(ReadTimeoutException.java:26)
        at io.micronaut.http.client.DefaultHttpClient.lambda$null$29(DefaultHttpClient.java:1083)
        at io.reactivex.internal.operators.flowable.FlowableOnErrorNext$OnErrorNextSubscriber.onError(FlowableOnErrorNext.java:103)
        at io.reactivex.internal.operators.flowable.FlowableTimeoutTimed$TimeoutSubscriber.onTimeout(FlowableTimeoutTimed.java:139)
        at io.reactivex.internal.operators.flowable.FlowableTimeoutTimed$TimeoutTask.run(FlowableTimeoutTimed.java:170)
        at io.reactivex.internal.schedulers.ScheduledRunnable.run(ScheduledRunnable.java:66)
        at io.reactivex.internal.schedulers.ScheduledRunnable.call(ScheduledRunnable.java:57)
        at java.util.concurrent.FutureTask.run(FutureTask.java:266)
        at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.access$201(ScheduledThreadPoolExecutor.java:180)
        at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:293)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
        at java.lang.Thread.run(Thread.java:748)
    ```
