package com.example.springdemo.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.example.springdemo.model.SysPermission;
import com.example.springdemo.model.SysUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import lombok.Data;

@Data
public class LoginUser extends SysUser implements UserDetails {

  private List<SysPermission> permissions;

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission())).map(p -> new SimpleGrantedAuthority(p.getPermission())).collect(Collectors.toSet());
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return true;
  }

}