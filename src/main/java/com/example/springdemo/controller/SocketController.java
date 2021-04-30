package com.example.springdemo.controller;

import com.example.springdemo.common.Result;
import com.example.springdemo.pkg.socket.SocketClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("socket")
public class SocketController {

    @GetMapping("/msg")
    public Result sendMsg(String uuid, String msg) {
        SocketClient.sendMsg(uuid, msg);
        // SocketServer.sendMessage(uuid, msg);
        return Result.ok();
    }
}
