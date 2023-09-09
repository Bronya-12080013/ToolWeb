package com.lan.website.service.impl;

import com.lan.website.mbg.mapper.IpsetMapper;
import com.lan.website.mbg.model.Ipset;
import com.lan.website.service.IpsetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IpsetServiceImpl implements IpsetService {
    @Autowired
    IpsetMapper ipsetMapper;

    @Override
    public List<Ipset> select() {
      return ipsetMapper.select();
    }

    @Override
    public Ipset selectip(String ip) {
        return ipsetMapper.selectip(ip);
    }

    @Override
    public int insert(Ipset ipset) {
        return ipsetMapper.insert(ipset);
    }

    @Override
    public int updateByPrimaryKeySelective(Ipset ipset) {
        return ipsetMapper.updateByPrimaryKeySelective(ipset);
    }
}
