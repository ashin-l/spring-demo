package com.example.springdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springdemo.model.JobModel;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobModelDao extends BaseMapper<JobModel> {
}
