package com.itheima.controller;

import com.itheima.pojo.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("permission")
@Controller
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("findAll")
    public ModelAndView findAll(){
        List<Permission> permissionList=permissionService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("permissionList",permissionList);
        modelAndView.setViewName("permission-list");
        return modelAndView;
    }

    @RequestMapping("findParentId")
    public ModelAndView findParentId(){
        List<Permission> parentList=permissionService.findParentList();

//        for (Permission permission : parentList) {
//            System.out.println("1");
//            System.out.println(parentList);
//        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("parentList",parentList);
        modelAndView.setViewName("permission-add");
        return modelAndView;
    }

    @RequestMapping("save")
    public String save(Permission permission){
        permissionService.save(permission);

        return "redirect:/permission/findAll";
    }
}
