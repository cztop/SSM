package com.itheima.dao;

import com.itheima.pojo.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;


public interface ProductDao{
    @Select("select * from product")
    public List<Product> findAll();

    @Insert("insert into product " +
            " values(product_seq.nextval,#{productNum},#{productName},#{cityName},#{departureTime}," +
            "#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    @Select("select * from product where id=#{id}")
    Product findById(Long id);

    @Update("update product set productNum = #{productNum},productName=#{productName}," +
            "cityName=#{cityName},departureTime=#{departureTime}," +
            "productPrice=#{productPrice},productDesc=#{productDesc}," +
            "productStatus=#{productStatus} where id = #{id}  ")
    public void update(Product product);


    @Delete("delete from product where id=#{id}")
    void deleteById(Long id);

    @Select("select Count(1) from product")
    Integer countSize();

    /**
     * 使用的orcale数据库，所以我们使用分页时需要借助行号，rowNum
     * @param startIndex
     * @param endIndex
     * @return
     */
    @Select("select rt.* from (select p.*,rownum rn from product p) rt " +
            "where rt.rn between #{param1} and #{param2}")
    List<Product> findByPage(Integer startIndex, Integer endIndex);
}
