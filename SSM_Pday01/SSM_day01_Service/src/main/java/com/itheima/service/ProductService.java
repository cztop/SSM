package com.itheima.service;



import com.github.pagehelper.PageInfo;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Product;

import java.util.List;

/**
 *
 */
public interface ProductService {
    public List<Product> findAll();


    /**
     * 保存数据
     * @param product
     */
    void save(Product product);

    /**
     * 数据回显
     * @param id
     * @return
     */
    Product findById(Long id);

    /**
     * 数据修改
     * @param product
     */
    void update(Product product);

    /**
     * 单个数据删除
     * @param id
     */
    void deleteById(Long id);

    /**
     * 数据删除
     * @param ids
     */
    void delSelected(Long[] ids);


    PageBean<Product> findByPage(Integer pageNum, Integer pageSize);

    PageInfo<Product> findByPageHelper(Integer pageNum, Integer pageSize);
}
