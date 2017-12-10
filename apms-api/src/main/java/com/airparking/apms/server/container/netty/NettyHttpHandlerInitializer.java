package com.airparking.apms.server.container.netty;

import com.airparking.apms.server.handler.ServiceHandler;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelPipeline;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.handler.codec.http.HttpContentCompressor;
//import io.netty.handler.codec.http.HttpRequestDecoder;
//import io.netty.handler.codec.http.HttpResponseEncoder;
//import io.netty.handler.ssl.SslContext;
//import io.netty.util.concurrent.DefaultEventExecutorGroup;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.channel.ChannelInitializer;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.channel.ChannelPipeline;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.channel.socket.SocketChannel;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.HttpContentCompressor;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.HttpRequestDecoder;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.codec.http.HttpResponseEncoder;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.ssl.SslContext;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.util.concurrent.DefaultEventExecutorGroup;

/**
 * Created by ryan on 12/30/15.
 */
public class NettyHttpHandlerInitializer extends ChannelInitializer<SocketChannel> {

    private SslContext sslContext;

    private DefaultEventExecutorGroup businessGroup;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        if (sslContext != null) {
            pipeline.addLast(sslContext.newHandler(ch.alloc()));
        }

        pipeline.addLast("request-decoder", new HttpRequestDecoder());
        pipeline.addLast("response-encode", new HttpResponseEncoder());
        // remove it if don't want automatic content compression
        pipeline.addLast(new HttpContentCompressor());
//        pipeline.addLast(new ServiceHandler());
        pipeline.addLast(businessGroup, new ServiceHandler());
    }

    public SslContext getSslContext() {
        return sslContext;
    }

    public DefaultEventExecutorGroup getBusinessGroup() {
        return businessGroup;
    }

    public void setBusinessGroup(DefaultEventExecutorGroup businessGroup) {
        this.businessGroup = businessGroup;
    }
}
