package com.itheima.dao;

import com.itheima.pojo.Order;
import com.itheima.pojo.Product;
import org.apache.ibatis.annotations.*;


import java.util.List;

public interface OrderDao {
   /* property：pojo的属性
    column:数据库查询的列
    javaType：属性的类型
    @one配置1对1的映射
     select:延时加载使用的哪个方法
    */
   @Select("select * from orders")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "product",column = "productId",javaType = Product.class,
            one = @One(select = "com.itheima.dao.ProductDao.findById"))
    })
    List<Order> findAll();

  /* 不使用主键回显语句：
  @Insert("insert into orders values (order_seq.nextval,#{orderNum},#{orderTime},#{peopleCount}," +
           "#{orderDesc},#{payType},#{orderStatus},#{product.id})")*/
  @Insert("insert into orders values (#{id},#{orderNum},#{orderTime},#{peopleCount}," +
          "#{orderDesc},#{payType},#{orderStatus},#{product.id})")
   @SelectKey(keyProperty = "id",keyColumn = "id",before = true,resultType = Long.class,
          statement = "select order_seq.nextval from dual")
    void save(Order order);
}
