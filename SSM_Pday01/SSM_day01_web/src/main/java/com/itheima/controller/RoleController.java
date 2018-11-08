package com.itheima.controller;

import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.service.PermissionService;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("role")
@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("findAll")
    public ModelAndView findAll(){
        List<Role> roleList=roleService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(roleList);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    @RequestMapping("save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:/role/findAll";
    }

    /**
     * 用于角色列表添加权限的数据回显
     *
     * @return
     */
    @RequestMapping("addPermission4RoleUI")
    public ModelAndView addPermissionUI(Integer id){
        List<Permission> permissionList=permissionService.findAll();

        Role role=roleService.findById(id);
        List<Permission> rolePermissionList = role.getPermissionList();
        //System.out.println("1"+"-------------------"+rolePermissionList);
        StringBuffer sb = new StringBuffer();
        for (Permission permission : rolePermissionList) {
            sb.append(",");
            sb.append(permission.getId());
            sb.append(",");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("permissionList",permissionList);
        modelAndView.addObject("rolePermissionList",sb.toString());

        modelAndView.addObject("roleId",role.getId());
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }

    @RequestMapping("saveRolePermission")
    public String saveRolePermission(Integer roleId,Integer[] ids){
        roleService.saveRolePermission(roleId,ids);
        return "redirect:/role/findAll";
    }
}
