package com.itheima.service;

import com.itheima.pojo.Role;
import com.itheima.pojo.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<SysUser> findAll();

    void save(SysUser user);

    SysUser isUniqueUsername(String username);

    SysUser findById(Integer id);


    void saveRole4User(Integer userId, Integer[] roleIds);
}
