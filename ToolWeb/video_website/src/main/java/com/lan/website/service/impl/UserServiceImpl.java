package com.lan.website.service.impl;

import com.lan.website.mbg.mapper.UserMapper;
import com.lan.website.mbg.model.User;
import com.lan.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service // @Service(value="UserService") //这个名字应该能自动识别到的吧 //这里加了反而Controller里会报错
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(String id) {        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User selectByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    //=================== 这两个只是要返回的类型不同罢了
    @Override
    public User selectLoginUser(Map map) {
        //他竟然不会自己返回null？？？
        User user;
        try {
            user =  userMapper.selectLoginUser(map);

        }catch (Exception e)
        {
            return null;
        }
        return user;
    }

    @Override
    public int selectUser(Map map) {
        User user = userMapper.selectLoginUser(map);
        if(user==null)
            return 0;
        else
            return 1;
    }
    //===========================

    @Override
    public int selectUser(String username) {
        return userMapper.selectByUserName(username);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }
}
