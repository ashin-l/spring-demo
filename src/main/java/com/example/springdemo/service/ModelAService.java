package com.example.springdemo.service;

import java.util.List;

import com.example.springdemo.model.ModelA;


public interface ModelAService {
    List<ModelA> list();

	void add(ModelA a);

	void update(ModelA a);    
}