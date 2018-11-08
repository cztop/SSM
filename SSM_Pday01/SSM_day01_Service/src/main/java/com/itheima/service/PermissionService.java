package com.itheima.service;

import com.itheima.pojo.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();

    List<Permission> findParentList();

    void save(Permission permission);
}
