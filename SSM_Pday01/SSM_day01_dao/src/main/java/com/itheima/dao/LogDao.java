package com.itheima.dao;

import com.itheima.pojo.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao {
    @Insert("insert into sys_log values(log_seq.nextval ,#{visitTime},#{username},#{ip},#{method})")
    public void save(SysLog log);
}
