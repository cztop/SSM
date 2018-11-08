package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.pojo.Role;
import com.itheima.pojo.SysUser;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      /*  // 先设置假的权限
        List<GrantedAuthority> authorities = new ArrayList();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        // 通过用户名查询密码
        SysUser sysUser = userDao.findByUsername(username);
        //判断认证是否成功
        if(sysUser!=null){
            User user = new User(username,sysUser.getPassword(),authorities);
            return user;
        }*/

      //设置真的权限
        // 通过用户名查询密码
        SysUser sysUser = userDao.findByUsername(username);
        if(sysUser!=null){
            List<GrantedAuthority> authorities = new ArrayList();
            List<Role> roleList = sysUser.getRoleList();
            for (Role role : roleList) {
                authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
            }
            User user = new User(username,sysUser.getPassword(),authorities);
            return user;
        }
        return null;
    }

    /**
     * 查询所有用户展示
     * @return
     */
    public List<SysUser> findAll() {
        return userDao.findAll();
    }

    /**
     * 添加用户
     * @param user
     */
    public void save(SysUser user) {
        userDao.save(user);
    }


    /**
     * 为用户添加角色
     * 清楚角色信息，新建角色信息
     *  @param userId
     * @param roleIds
     */
    public void saveRole4User(Integer userId, Integer[] roleIds) {
        userDao.delAllRoleInfo(userId);
        if (roleIds!=null){
            for (Integer roleId : roleIds) {
                userDao.addRole4UserByRoleId(userId,roleId);
            }
        }
    }

    /**
     * 查询用户详情信息---详情页面的数据准备
     * 数据回显也使用该方法
     * 查询某个用户的所有信息
     * * @return
     */
    public SysUser findById(Integer id) {
        return userDao.findById(id);
    }

    /**
     * 查询判断用户是否唯一
     * @param username
     * @return
     */
    public SysUser isUniqueUsername(String username) {
        return userDao.findUserByUsernameisUnique(username);
    }
}
