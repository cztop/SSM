package com.itheima.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Product;
import com.itheima.dao.ProductDao;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * 修改---数据回显
     * @param id
     * @return
     */
    public Product findById(Long id) {
        return productDao.findById(id);
    }

    public PageInfo<Product> findByPageHelper(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Product> productList=productDao.findAll();
        PageInfo<Product> pageInfo = new PageInfo(productList);
        return pageInfo;
    }

    /**
     * 手动的分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageBean<Product> findByPage(Integer pageNum, Integer pageSize) {
        PageBean<Product> productPageBean = new PageBean<Product>();
        //封装pageBean
        productPageBean.setPageNum(pageNum);
        productPageBean.setPageSize(pageSize);
        Integer totalSize=productDao.countSize();
        productPageBean.setTotalSize(totalSize);
        Integer totalPage = (int) Math.ceil(totalSize * 1.0 / pageSize);
        productPageBean.setTotalPage(totalPage);

        //传每页的起始索引与结束索引
        int startIndex = (pageNum-1)*pageSize+1;
        int endIndex=pageNum*pageSize;
        List<Product> productList=productDao.findByPage(startIndex,endIndex);
        productPageBean.setProductList(productList);
        return productPageBean;
    }

    /**
     * 数据删除
     * @param ids
     */
    public void delSelected(Long[] ids) {
        if(ids!=null&&ids.length>0){
            for (Long id : ids) {
                productDao.deleteById(id);
            }
        }
    }

    /**
     * 单个数据删除
     * @param id
     */
    public void deleteById(Long id) {
        productDao.deleteById(id);
    }

    /**
     * 数据修改
     * @param product
     */
    public void update(Product product) {
    productDao.update(product);
    }


    /**
     * 数据保存
     * @param product
     */
    public void save(Product product) {
        productDao.save(product);
    }

    /**
     * 查询所有
     * @return
     */
    public List<Product> findAll() {
        return productDao.findAll();
    }


}
