package com.itheima.service.impl;

import com.itheima.dao.LogDao;
import com.itheima.pojo.SysLog;
import com.itheima.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
        @Autowired
        private LogDao logDao;
    public void save(SysLog log) {
        logDao.save(log);
    }
}
