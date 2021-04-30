package com.example.springdemo.pkg.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 添加编解码
        // ch.pipeline().addLast("decoder", new
        // StringDecoder(CharsetUtil.US_ASCII));
        // ch.pipeline().addLast("encoder", new
        // StringEncoder(CharsetUtil.US_ASCII));
        ch.pipeline().addLast(new NettyServerHandler());
    }
}