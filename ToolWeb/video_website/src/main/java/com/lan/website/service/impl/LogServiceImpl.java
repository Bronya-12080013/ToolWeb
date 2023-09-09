package com.lan.website.service.impl;

import com.lan.website.mbg.mapper.LogMapper;
import com.lan.website.mbg.model.Log;
import com.lan.website.mbg.model.LogExample;
import com.lan.website.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public List<Log> select() {
        return logMapper.select();
    }

    @Override
    public List<Log> selectAdminLog() {
        return logMapper.selectadminlog();
    }

    @Override
    public List<Log> selectByUsername(String username) {
        return logMapper.selectbyusername(username);
    }

    @Override
    public List<Log> selectAdminLogByUsername(String username) {
        return logMapper.selectadminlogbyusername(username);
    }

    @Override
    public void insert(Log log) {
        logMapper.insert(log);
    }

    @Override
    public int updateByPrimaryKeySelective(Log log) {
        return logMapper.updateByPrimaryKeySelective(log);
    }
}
