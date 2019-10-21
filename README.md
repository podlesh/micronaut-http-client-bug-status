

1. Build:
    ```bash
    ./gradlew build
    ```
2. Check that https://httpstat.us/ is running and available:
    ```bash
    java -jar build/libs/httpclient-0.2-all.jar 200 400 409 500
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
    java -jar build/libs/httpclient-0.2-all.jar 519
    ``` 
    Expected behavior: this line is the very last one
    ```
    waiting for 10s for any timeout
    ```
    Bad output:
    ``` 
    waiting for 10s for any timeout
    io.reactivex.exceptions.UndeliverableException: The exception could not be delivered to the consumer because it has already canceled/disposed the flow or the exception has nowhere to go to begin with. Further reading: https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0#error-handling | io.micronaut.http.client.exceptions.ReadTimeoutException: Read Timeout
        at io.reactivex.plugins.RxJavaPlugins.onError(RxJavaPlugins.java:367)
        at io.reactivex.internal.operators.flowable.FlowableCreate$BaseEmitter.onError(FlowableCreate.java:275)
        at io.micronaut.http.client.DefaultHttpClient$10.exceptionCaught(DefaultHttpClient.java:1979)
        at io.netty.channel.AbstractChannelHandlerContext.invokeExceptionCaught(AbstractChannelHandlerContext.java:297)
        at io.netty.channel.AbstractChannelHandlerContext.invokeExceptionCaught(AbstractChannelHandlerContext.java:276)
        at io.netty.channel.AbstractChannelHandlerContext.fireExceptionCaught(AbstractChannelHandlerContext.java:268)
        at io.netty.channel.CombinedChannelDuplexHandler$DelegatingChannelHandlerContext.fireExceptionCaught(CombinedChannelDuplexHandler.java:426)
        at io.netty.channel.ChannelHandlerAdapter.exceptionCaught(ChannelHandlerAdapter.java:92)
        at io.netty.channel.CombinedChannelDuplexHandler$1.fireExceptionCaught(CombinedChannelDuplexHandler.java:147)
        at io.netty.channel.ChannelInboundHandlerAdapter.exceptionCaught(ChannelInboundHandlerAdapter.java:143)
        at io.netty.channel.CombinedChannelDuplexHandler.exceptionCaught(CombinedChannelDuplexHandler.java:233)
        at io.netty.channel.AbstractChannelHandlerContext.invokeExceptionCaught(AbstractChannelHandlerContext.java:297)
        at io.netty.channel.AbstractChannelHandlerContext.invokeExceptionCaught(AbstractChannelHandlerContext.java:276)
        at io.netty.channel.AbstractChannelHandlerContext.fireExceptionCaught(AbstractChannelHandlerContext.java:268)
        at io.netty.handler.timeout.ReadTimeoutHandler.readTimedOut(ReadTimeoutHandler.java:98)
        at io.netty.handler.timeout.ReadTimeoutHandler.channelIdle(ReadTimeoutHandler.java:90)
        at io.netty.handler.timeout.IdleStateHandler$ReaderIdleTimeoutTask.run(IdleStateHandler.java:505)
        at io.netty.handler.timeout.IdleStateHandler$AbstractIdleTask.run(IdleStateHandler.java:477)
        at io.netty.util.concurrent.PromiseTask$RunnableAdapter.call(PromiseTask.java:38)
        at io.netty.util.concurrent.ScheduledFutureTask.run(ScheduledFutureTask.java:139)
        at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:163)
        at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:510)
        at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:518)
        at io.netty.util.concurrent.SingleThreadEventExecutor$6.run(SingleThreadEventExecutor.java:1044)
        at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
        at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
        at java.lang.Thread.run(Thread.java:748)
    Caused by: io.micronaut.http.client.exceptions.ReadTimeoutException: Read Timeout
        at io.micronaut.http.client.exceptions.ReadTimeoutException.<clinit>(ReadTimeoutException.java:26)
        ... 25 more
    Exception in thread "nioEventLoopGroup-1-2" io.reactivex.exceptions.UndeliverableException: The exception could not be delivered to the consumer because it has already canceled/disposed the flow or the exception has nowhere to go to begin with. Further reading: https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0#error-handling | io.micronaut.http.client.exceptions.ReadTimeoutException: Read Timeout
        at io.reactivex.plugins.RxJavaPlugins.onError(RxJavaPlugins.java:367)
        at io.reactivex.internal.operators.flowable.FlowableCreate$BaseEmitter.onError(FlowableCreate.java:275)
        at io.micronaut.http.client.DefaultHttpClient$10.exceptionCaught(DefaultHttpClient.java:1979)
        at io.netty.channel.AbstractChannelHandlerContext.invokeExceptionCaught(AbstractChannelHandlerContext.java:297)
        at io.netty.channel.AbstractChannelHandlerContext.invokeExceptionCaught(AbstractChannelHandlerContext.java:276)
        at io.netty.channel.AbstractChannelHandlerContext.fireExceptionCaught(AbstractChannelHandlerContext.java:268)
        at io.netty.channel.CombinedChannelDuplexHandler$DelegatingChannelHandlerContext.fireExceptionCaught(CombinedChannelDuplexHandler.java:426)
        at io.netty.channel.ChannelHandlerAdapter.exceptionCaught(ChannelHandlerAdapter.java:92)
        at io.netty.channel.CombinedChannelDuplexHandler$1.fireExceptionCaught(CombinedChannelDuplexHandler.java:147)
        at io.netty.channel.ChannelInboundHandlerAdapter.exceptionCaught(ChannelInboundHandlerAdapter.java:143)
        at io.netty.channel.CombinedChannelDuplexHandler.exceptionCaught(CombinedChannelDuplexHandler.java:233)
        at io.netty.channel.AbstractChannelHandlerContext.invokeExceptionCaught(AbstractChannelHandlerContext.java:297)
        at io.netty.channel.AbstractChannelHandlerContext.invokeExceptionCaught(AbstractChannelHandlerContext.java:276)
        at io.netty.channel.AbstractChannelHandlerContext.fireExceptionCaught(AbstractChannelHandlerContext.java:268)
        at io.netty.handler.timeout.ReadTimeoutHandler.readTimedOut(ReadTimeoutHandler.java:98)
        at io.netty.handler.timeout.ReadTimeoutHandler.channelIdle(ReadTimeoutHandler.java:90)
        at io.netty.handler.timeout.IdleStateHandler$ReaderIdleTimeoutTask.run(IdleStateHandler.java:505)
        at io.netty.handler.timeout.IdleStateHandler$AbstractIdleTask.run(IdleStateHandler.java:477)
        at io.netty.util.concurrent.PromiseTask$RunnableAdapter.call(PromiseTask.java:38)
        at io.netty.util.concurrent.ScheduledFutureTask.run(ScheduledFutureTask.java:139)
        at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:163)
        at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:510)
        at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:518)
        at io.netty.util.concurrent.SingleThreadEventExecutor$6.run(SingleThreadEventExecutor.java:1044)
        at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
        at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
        at java.lang.Thread.run(Thread.java:748)
    Caused by: io.micronaut.http.client.exceptions.ReadTimeoutException: Read Timeout
        at io.micronaut.http.client.exceptions.ReadTimeoutException.<clinit>(ReadTimeoutException.java:26)
        ... 25 more
    ```
3. Try it with several non-standard codes and limit:
    ```bash
    java -jar build/libs/httpclient-0.2-all.jar 519 519 588 588 -c 1
    ``` 
    Expected behavior: print requested number of results (1) and no error.
    ```
    OK-ish: request to https://httpstat.us/519 failed with exception java.lang.IllegalArgumentException: Invalid HTTP status code: 519
    waiting for 10s for any timeout
    ```
    Bad output:
    ``` 
    flow cancelled
    OK-ish: request to https://httpstat.us/519 failed with exception java.lang.IllegalArgumentException: Invalid HTTP status code: 519
    waiting for 10s for any timeout
    java.lang.IllegalArgumentException: Invalid HTTP status code: 588
        at io.micronaut.http.HttpStatus.valueOf(HttpStatus.java:146)
        at io.micronaut.http.client.DefaultHttpClient$10.channelRead0(DefaultHttpClient.java:1804)
        at io.micronaut.http.client.DefaultHttpClient$10.channelRead0(DefaultHttpClient.java:1791)
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
    Exception in thread "nioEventLoopGroup-1-4" java.lang.IllegalArgumentException: Invalid HTTP status code: 588
        at io.micronaut.http.HttpStatus.valueOf(HttpStatus.java:146)
        at io.micronaut.http.client.DefaultHttpClient$10.channelRead0(DefaultHttpClient.java:1804)
        at io.micronaut.http.client.DefaultHttpClient$10.channelRead0(DefaultHttpClient.java:1791)
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
    ```
