package com.example.springdemo.dao;

import java.util.List;

import com.example.springdemo.model.SysUser;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

  Long getTotal();

  //@Select("select * from sys_user t order by t.id limit #{limit} offset #{offset}")
  List<SysUser> getUserList();

  // ${username} 使用字符串拼接， #{username} 使用占位符
  // @Select("select * from sys_user t where t.username = ${username}")
  @Select("select * from sys_user t where t.username = #{username}")
  SysUser getUser(String username);

  //@Options(useGeneratedKeys = true, keyProperty = "id")
  //@Insert("Insert into sys_user(username,password) values(#{username},#{password})")
  int save(SysUser user);

  int updateUser(SysUser user);

  @Delete("delete from sys_user where id = #{id}")
  int delete(Long id);
}