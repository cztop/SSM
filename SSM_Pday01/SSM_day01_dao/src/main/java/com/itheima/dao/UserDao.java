package com.itheima.dao;

import com.itheima.pojo.Role;
import com.itheima.pojo.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 *
 */
public interface UserDao {
    @Select("select * from sys_user where username = #{username} and status=1")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(column = "id",property = "roleList",javaType = List.class,
                    many=@Many(select = "com.itheima.dao.RoleDao.findRoleByUid",fetchType = FetchType.LAZY))
    })
    SysUser findByUsername(String username);

    @Select("select * from sys_user order by id")
    List<SysUser> findAll();

    @Insert("insert into sys_user values(#{id},#{username},#{email},#{password},#{phoneNum},#{status})")
    @SelectKey(keyProperty = "id",keyColumn = "id",before = true,resultType = Long.class,
            statement = "select user_seq.nextval from dual")
    void save(SysUser user);

    @Select("select * from sys_user where username = #{username}")
    SysUser findUserByUsernameisUnique(String username);

    /**
     * 详情页面--数据准备
     * 用户与角色是多对多关系，但是在这里我们认为是一个用户对应多个角色的关系
     * 采用result注解使用映射关系解决一对多练习，相当于xml配置文件的resultMap
     * @param id
     * @return
     */
    @Select("select * from sys_user where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(column = "id",property = "roleList",javaType = List.class,
            many=@Many(select = "com.itheima.dao.RoleDao.findRoleByUid",fetchType = FetchType.LAZY))
    })
    SysUser findById(Integer id);


    @Delete("delete from sys_user_role where userid=#{userId}")
    void delAllRoleInfo(Integer userId);

    @Insert("insert into sys_user_role (userid,roleid) values(#{param1},#{param2})")
    void addRole4UserByRoleId(Integer userId, Integer roleId);
}
