package com.itheima.controller;

import com.itheima.pojo.Order;
import com.itheima.pojo.Product;
import com.itheima.service.OrderService;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @RequestMapping("findAll")
    public ModelAndView findAll(){

        List<Order> orderList=orderService.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orderList",orderList);
        modelAndView.setViewName("order-list");
        return  modelAndView;
    }

    /**
     * 添加功能，数据准备
     * 准备产品名称
     * @return
     */
    @RequestMapping("addOrder")
    public ModelAndView addOrderReday(){
        List<Product> productList = productService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productList",productList);
        modelAndView.setViewName("order-add");
        return  modelAndView;
    }

    @RequestMapping("save")
    public String save(Order order){
        orderService.save(order);
        return  "redirect:/order/findAll";
    }
}
