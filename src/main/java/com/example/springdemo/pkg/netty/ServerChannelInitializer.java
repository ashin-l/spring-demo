package com.example.springdemo.pkg.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 添加编解码
        // socketChannel.pipeline().addLast("decoder", new
        // StringDecoder(CharsetUtil.US_ASCII));
        // socketChannel.pipeline().addLast("encoder", new
        // StringEncoder(CharsetUtil.US_ASCII));
        socketChannel.pipeline().addLast(new NettyServerHandler());
    }
}