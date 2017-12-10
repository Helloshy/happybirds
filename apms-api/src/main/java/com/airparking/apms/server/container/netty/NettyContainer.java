package com.airparking.apms.server.container.netty;

import java.net.InetAddress;
import java.net.UnknownHostException;

//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.Channel;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.ssl.SslContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.airparking.apms.server.container.Container;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.bootstrap.ServerBootstrap;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.channel.Channel;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.channel.ChannelInitializer;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.channel.EventLoopGroup;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.channel.socket.SocketChannel;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.channel.socket.nio.NioServerSocketChannel;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.handler.ssl.SslContext;

/**
 * Created by ryan on 12/30/15.
 */
public class NettyContainer implements Container {
    private static final Logger logger = LoggerFactory.getLogger(NettyContainer.class);

    private static final int DEFAULT_NETTY_PORT = 8090;
    private static final boolean SSL = false;

    private int port;
    private boolean enableSsl;
    private Channel channel;
    private String inetHost;

    private SslContext sslContext;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    private ChannelInitializer<SocketChannel> channelInitializer;

    public void start() {
        if (enableSsl) {
//            SelfSignedCertificate ssc = new SelfSignedCertificate();
//            sslContext = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        }

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(channelInitializer);


            if (StringUtils.isEmpty(inetHost)) {
                channel = bootstrap.bind(port).sync().channel();
            } else {
                channel = bootstrap.bind(InetAddress.getByName(inetHost), port).sync().channel();
            }

            logger.info("Netty container has been started at port " + port);

            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("Start netty container is failed!", e.getMessage());
        } catch (UnknownHostException e) {
            logger.error("Bind host is failed!", e.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public void stop() {
        try {
            channel.close();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isEnableSsl() {
        return enableSsl;
    }

    public void setEnableSsl(boolean enableSsl) {
        this.enableSsl = enableSsl;
    }

    public String getInetHost() {
        return inetHost;
    }

    public void setInetHost(String inetHost) {
        this.inetHost = inetHost;
    }

    public EventLoopGroup getBossGroup() {
        return bossGroup;
    }

    public void setBossGroup(EventLoopGroup bossGroup) {
        this.bossGroup = bossGroup;
    }

    public EventLoopGroup getWorkerGroup() {
        return workerGroup;
    }

    public void setWorkerGroup(EventLoopGroup workerGroup) {
        this.workerGroup = workerGroup;
    }

    public ChannelInitializer<SocketChannel> getChannelInitializer() {
        return channelInitializer;
    }

    public void setChannelInitializer(ChannelInitializer<SocketChannel> channelInitializer) {
        this.channelInitializer = channelInitializer;
    }
}
