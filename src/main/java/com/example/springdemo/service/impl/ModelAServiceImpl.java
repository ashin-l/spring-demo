package com.example.springdemo.service.impl;

import java.util.List;

import com.example.springdemo.dao.ModelADao;
import com.example.springdemo.model.ModelA;
import com.example.springdemo.service.ModelAService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelAServiceImpl implements ModelAService {

    @Autowired
    private ModelADao dao;

    @Override
    public List<ModelA> list() {
        return dao.list();
    }

    @Override
    public void add(ModelA a) {
        dao.add(a);
    }

    @Override
    public void update(ModelA a) {
        dao.update(a);
    }
    
}