package com.example.springdemo.dao;

import java.util.List;

import com.example.springdemo.model.ModelA;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ModelADao {
    List<ModelA> list();

	void add(ModelA a);

	void update(ModelA a);
}