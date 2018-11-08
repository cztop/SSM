package com.itheima.service.impl;

import com.itheima.dao.RoleDao;
import com.itheima.pojo.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /**
     * 删除已有的
     * 保存新建的权限角色
     * @param roleId
     * @param ids
     */
    public void saveRolePermission(Integer roleId, Integer[] ids) {
        roleDao.delAllPermission(roleId);
        for (Integer id : ids) {
            roleDao.saveRolePermission(roleId,id);
        }
    }

    /**
     * 角色列表添加权限之数据回显功能
     * 根据id查询角色所拥有的权限
     * @return
     */
    public Role findById(Integer id) {
       return roleDao.findById(id);
    }

    public void save(Role role) {
        roleDao.save(role);
    }

    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
