package com.itheima.service;

import com.itheima.pojo.Order;

import java.util.List;

public interface OrderService {

    public List<Order> findAll() ;

    void save(Order order);
}
