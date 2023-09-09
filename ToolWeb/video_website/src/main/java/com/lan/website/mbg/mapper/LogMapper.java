package com.lan.website.mbg.mapper;

import com.lan.website.mbg.model.Log;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LogMapper {
	List<Log> select();
	List<Log> selectadminlog();
	List<Log> selectbyusername(String username);
	List<Log> selectadminlogbyusername(String username);
	int insert(Log log);
	int updateByPrimaryKeySelective(Log log);

}
