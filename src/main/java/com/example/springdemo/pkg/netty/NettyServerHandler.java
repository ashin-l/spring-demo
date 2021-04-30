package com.example.springdemo.pkg.netty;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.example.springdemo.util.HexStringUtil;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    public static Map<String, ChannelHandlerContext> ctxMap = new ConcurrentHashMap<String, ChannelHandlerContext>();
    private static String NET_NUM = "5AA506FFFFFFFF00504C";
    private static String ON_CODE = "5AA50778595E0D0052109E";
    private static String OFF_CODE = "5AA50778595E0D0052008E";

    /**
     * 客户端连接会触发
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("Channel active......");
        var ipSocket = (InetSocketAddress) ctx.channel().remoteAddress();
        var ip = ipSocket.getAddress().getHostAddress();
        System.out.println(ip);
        ctxMap.put("lamp001", ctx);
    }

    /**
     * 客户端发消息会触发
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println(ByteBufUtil.hexDump(in));
    }

    /**
     * 发生异常触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public static void sendMessage(String keyid, String msg) {
        ChannelHandlerContext ctx = ctxMap.get(keyid);
        if (ctx != null) {
            System.out.println("发送消息：" + msg);
            ctx.write(msg);
            ctx.flush();
        }
        // writeAndFlush在这里没生效，没找到原因
    }

    public static void sendMessage(String msg) {
        ChannelHandlerContext ctx = ctxMap.get("lamp001");
        if (ctx != null) {
            ByteBuf buf = Unpooled.buffer();
            // buf.writeBytes(HexStringUtil.getHexBytes(ON_CODE));
            // buf.writeBytes(HexStringUtil.getHexBytes(OFF_CODE));
            buf.writeBytes(HexStringUtil.hexToBytes(msg));
            // ctx.write(buf);
            // ctx.flush();
            ctx.writeAndFlush(buf).addListener(new ChannelFutureListener() { // 获取当前的handle
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    StringBuilder sb = new StringBuilder("");

                    if (future.isSuccess()) {
                        log.info(sb.toString() + "回写成功.");
                    } else {
                        log.error(sb.toString() + "回写失败.");
                    }
                }
            });
        }
        // writeAndFlush在这里没生效，没找到原因
    }

}