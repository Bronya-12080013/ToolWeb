package com.lan.website.mbg.mapper;


import com.lan.website.mbg.model.Ipset;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IpsetMapper {
	List<Ipset> select();
	Ipset selectip(String ip);
	int insert(Ipset ipset);
	int updateByPrimaryKeySelective(Ipset ipset);
}
