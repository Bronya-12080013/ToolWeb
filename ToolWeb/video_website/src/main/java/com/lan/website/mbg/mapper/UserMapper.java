package com.lan.website.mbg.mapper;


import com.lan.website.mbg.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserMapper {
	List<User> selectAllUser();
	User selectLoginUser(Map map);
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);
    int selectByUserName(String username);
    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}