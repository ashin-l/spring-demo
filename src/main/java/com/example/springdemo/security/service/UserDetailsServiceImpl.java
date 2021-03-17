package com.example.springdemo.security.service;

import com.example.springdemo.dao.PermissionDao;
import com.example.springdemo.dao.UserDao;
import com.example.springdemo.model.SysUser;
import com.example.springdemo.security.entity.JwtUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author shuang.kou
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        var user = userDao.getUser(name);
        if (user == null) {
            throw new UsernameNotFoundException(name);
        }
        return new User(user.getUsername(), user.getPassword(), )
        // return new JwtUser(user);
    }

}
