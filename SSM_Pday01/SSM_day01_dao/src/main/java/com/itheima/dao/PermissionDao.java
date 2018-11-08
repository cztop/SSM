package com.itheima.dao;

import com.itheima.pojo.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;


public interface PermissionDao {

    @Select("select * from sys_permission")
    List<Permission> findAll();

    @Select("select * from sys_permission where pid=0")
    List<Permission> findParentList();

    @Insert("insert into sys_permission values(#{id},#{permissionName},#{url},#{pid})")
    @SelectKey(keyProperty = "id",keyColumn = "id",before = true,resultType = Long.class,
    statement = ("select permission_seq.nextval from dual"))
    void save(Permission permission);


    @Select("select p.* " +
            "from sys_permission p,sys_role_permission rp " +
            "where rp.roleid=#{id} and p.id=rp.permissionid ")
    List<Permission> findPermissionByRid(Integer id);
}
