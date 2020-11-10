package com.example.springdemo.controller;

import java.util.List;
import java.util.Map;

import com.example.springdemo.common.PageQuery;
import com.example.springdemo.common.RPage;
import com.example.springdemo.common.Result;
import com.example.springdemo.dto.UserInfo;
import com.example.springdemo.model.SysRoleUser;
import com.example.springdemo.model.SysUser;
import com.example.springdemo.service.RoleUserService;
import com.example.springdemo.service.UserService;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private RoleUserService roleuserService;

  @GetMapping("/info/{username}")
  @ResponseBody
  public String userInfo(@PathVariable String username, @RequestParam String password) {
    return username + " " + password;
  }

  @RequestMapping("/list")
  @ResponseBody
  public Result userList(PageQuery pageQuery) {
    PageHelper.startPage(pageQuery.getCurrentPage(), pageQuery.getPageSize());
    List<SysUser> data = userService.getUserList();
    return Result.ok(RPage.setPage(data));
  }

  // public Result userList(@RequestParam int page, @RequestParam int limit) {
  // System.out.println(page);
  // System.out.println(limit);
  // int offset = (page -1) * limit;
  // List<SysUser> userlist = userService.getUserList(limit, offset);
  // Long total = userService.getTotal();
  // Page<SysUser> p = new Page<SysUser>(total, userlist);
  // return Result.ok(p);
  // }

  @PostMapping("/add")
  @ResponseBody
  public Result addUser(@RequestBody Map params) {
    System.out.println(params);
    SysUser user = new SysUser();
    user.setUsername((String) params.get("username"));
    user.setPassword(new BCryptPasswordEncoder().encode((String) params.get("password")));
    //user.setPassword(MD5.crypt((String) params.get("password")));
    userService.save(user);
    System.out.println(params);
    if (params.get("roleid") != null) {
      SysRoleUser roleuser = new SysRoleUser();
      roleuser.setRoleId(Long.valueOf(String.valueOf(params.get("roleid"))));
      System.out.println(user.getId());
      roleuser.setUserId(user.getId());
      System.out.println(roleuser);
      roleuserService.addRoleUser(roleuser);
    }
    return Result.ok();
  }

  @PostMapping("/update")
  @ResponseBody
  public Result updateUser(@RequestBody UserInfo userInfo) {
    System.out.println(userInfo);
    SysUser user = new SysUser();
    user.setId(userInfo.getId());
    user.setUsername(userInfo.getUsername());
    userService.update(user);
    return Result.ok();
  }
}