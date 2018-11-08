package com.itheima.log;

import com.itheima.pojo.SysLog;
import com.itheima.pojo.SysUser;
import com.itheima.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 注解的方式配置日志的Aop
 */
@Controller
@Aspect
public class LogController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LogService logService;

    //配置切点
    @Pointcut("execution(* com.itheima.controller.*.*(..))")
    public void cut(){}


    /**
     * 日志功能之
     * 前置增强
     * 需要传入连接点
     * @param joinPoint
     */
    @Before("cut()")
    public void before(JoinPoint joinPoint){
        SysLog log = new SysLog();

        //id序列自动生成
        log.setVisitTime(new Date());//访问时间

        //从安全框架的上下文对象中获取用户名
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User) authentication.getPrincipal();
        log.setUsername(user.getUsername());

        //由监听器获取的请求信息中获取远程访问地址
        String ip = request.getRemoteAddr();
        log.setIp(ip);

        //由传入的连接点对象可以获取访问的方法
        Object target = joinPoint.getTarget();
        String className = target.getClass().getName();
        Signature signature = joinPoint.getSignature(); //获取签名
        String methodName = signature.getName();
        log.setMethod(className+"."+methodName);

        logService.save(log);
    }
}
