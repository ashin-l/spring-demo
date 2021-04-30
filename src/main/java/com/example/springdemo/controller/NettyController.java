package com.example.springdemo.controller;

import com.example.springdemo.common.Result;
import com.example.springdemo.pkg.netty.NettyServerHandler;
import com.example.springdemo.util.HexStringUtil;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("netty")
public class NettyController {

    @GetMapping("/msg")
    public Result sendMsg(String uuid, String msg) {
        // Integer startCode = 0x5AA5;
        // Integer len = 0x06;
        // System.out.println(Integer.toString(len, 16));
        // Integer netSn = 0xFFFFFFFF;
        // Integer sn = 0x00;
        // Integer opcode = 0x50;
        // Integer checkCode = 0x4C;
        // msg = Integer.toHexString(startCode) + Integer.toHexString(len) +
        // Integer.toHexString(netSn)
        // + Integer.toHexString(sn) + Integer.toHexString(opcode) +
        // Integer.toHexString(checkCode);
        // System.out.println(msg);
        // System.out.println(tmp);
        // System.out.println(tmp.toString());
        // NettyServerHandler.sendMessage(uuid, msg);
        // var tmp = HexStringUtil.getHexBytes(msg);
        NettyServerHandler.sendMessage(msg);
        return Result.ok();
    }
}
