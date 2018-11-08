package com.itheima.service.impl;

import com.itheima.dao.PermissionDao;
import com.itheima.pojo.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao  permissionDao;

    /**
     * 添加权限之父权限id
     * 数据回显
     * @return
     */
    public List<Permission> findParentList() {
        return permissionDao.findParentList();
    }

    /**
     * 添加权限
     * @param permission
     */
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    /**
     * 查询权限列表
     * @return
     */
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }
}
