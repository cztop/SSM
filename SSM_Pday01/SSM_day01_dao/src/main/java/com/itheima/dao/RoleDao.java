package com.itheima.dao;

import com.itheima.pojo.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface RoleDao {
    @Select("select * from sys_role")
    List<Role> findAll();


    @Insert("insert into sys_role values(#{id},#{roleName},#{roleDesc})")
    @SelectKey(keyProperty = "id",keyColumn = "id",before = true,
    resultType = Long.class,statement = "select role_seq.nextval from dual")
    void save(Role role);


    @Select("select r.* " +
            "from sys_role r,sys_user_role ur " +
            "where ur.userid=#{id} and ur.roleid=r.id")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "permissionList",javaType = List.class,
            many = @Many(select = "com.itheima.dao.PermissionDao.findPermissionByRid",fetchType = FetchType.LAZY))
    })
    List<Role> findRoleByUid(Integer id);


    @Select("select * from sys_role where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "permissionList",javaType = List.class,
                    many = @Many(select = "com.itheima.dao.PermissionDao.findPermissionByRid",fetchType = FetchType.LAZY))
    })
    Role findById(Integer id);


    @Delete("delete from sys_role_permission where roleId=#{roleId}")
    void delAllPermission(Integer roleId);

    @Insert("insert into sys_role_permission (roleId,permissionId) values(#{param1},#{param2})")
    void saveRolePermission(Integer roleId, Integer id);
}

