package com.lan.website.service;

import com.lan.website.mbg.model.Log;

import java.util.List;

public interface LogService{
    List<Log> select();
    List<Log> selectAdminLog();
    List<Log> selectByUsername(String username);
    List<Log> selectAdminLogByUsername(String username);
    void insert(Log log);
    int updateByPrimaryKeySelective(Log log);
}
