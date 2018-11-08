package com.itheima.service.impl;

import com.itheima.dao.OrderDao;
import com.itheima.pojo.Order;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    public void save(Order order) {
        orderDao.save(order);
    }

    public List<Order> findAll() {

        return orderDao.findAll();
    }
}
