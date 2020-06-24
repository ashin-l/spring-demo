package com.example.springdemo.controller;

import com.example.springdemo.common.Result;
import com.example.springdemo.model.ModelA;
import com.example.springdemo.service.ModelAService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("modela")
public class ModelAController {
    
    @Autowired
    private ModelAService service;

    @GetMapping("/list")
    public Result list() {
        var data = service.list();
        return Result.ok(data);
    }

    @PostMapping("/add")
    public Result add(@RequestBody ModelA a) {
        service.add(a);
        return Result.ok();
    }

    @PostMapping("/update")
    public Result update(@RequestBody ModelA a) {
        service.update(a);
        return Result.ok();
    }
}