package com.example.springdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springdemo.model.LampStrategy;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LampStrategyDao extends BaseMapper<LampStrategy> {

}
