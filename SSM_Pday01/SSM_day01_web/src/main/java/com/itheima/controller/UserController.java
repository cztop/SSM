package com.itheima.controller;

import com.itheima.pojo.Role;
import com.itheima.pojo.SysUser;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("user")
@Secured({"ROLE_admin"})
public class UserController {
    @Autowired
    private UserService userService;


    @Autowired
    private RoleService roleService;

    /**
     * 返回所有的用户
     *
     * @return
     */
    @RequestMapping("findAll")
    public ModelAndView findAll() {
        List<SysUser> sysUserList = userService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sysUserList",sysUserList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @RequestMapping("save")
    public String save(SysUser user) {
        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        userService.save(user);
        return "redirect:/user/findAll";
    }

    @RequestMapping("isUniqueUsername")
    @ResponseBody
    public String isUniqueUsername(String username) {
        SysUser user = userService.isUniqueUsername(username);
        if (user != null) {
            // System.out.println("true");
            return "true";
        } else {
            // System.out.println("false");
            return "false";
        }
    }


    /**
     * 向前端返回用户名
     *
     * @param request
     * @return
     */
    @RequestMapping("showUSername")
    @ResponseBody
    public String showUsername(HttpServletRequest request) {
        String username = null;
        //获取SecurityContext上下文对象
        SecurityContext context = SecurityContextHolder.getContext();
        //获取认证对象
        Authentication authentication = context.getAuthentication();
        //获取重要对象（User）
        User user = (User) authentication.getPrincipal();
        username = user.getUsername();
        return username;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(Integer id) {
        SysUser user = userService.findById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

    /**
     * 回显数据
     * 查询所有的角色
     * 查询该用户有的角色
     *
     * @return
     */
    @RequestMapping("addRoleUI")
    public ModelAndView addRoleUI(Integer id) {
        List<Role> roleList = roleService.findAll();
        SysUser user = userService.findById(id);

        StringBuffer sb = new StringBuffer();
        for (Role role : user.getRoleList()) {
            sb.append(",");
            sb.append(role.getId());
            sb.append(",");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userWithRole2Str", sb.toString());
        modelAndView.addObject("roleList", roleList);
        //方便保存数据时提供uid
        modelAndView.addObject("userId", user.getId());

        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }

    /**
     * @param userId
     * @param roleIds
     * @return
     */
    @RequestMapping("saveRole4User")
    public String saveRole4User(Integer userId,Integer[] roleIds){
        userService.saveRole4User(userId,roleIds);
        return "redirect:/user/findAll";
    }
}
