package com.lan.website.service;

import com.lan.website.mbg.model.Ipset;

import java.util.List;

public interface IpsetService {
    List<Ipset> select();
    Ipset selectip(String ip);
    int insert(Ipset ipset);
    int updateByPrimaryKeySelective(Ipset ipset);
}
