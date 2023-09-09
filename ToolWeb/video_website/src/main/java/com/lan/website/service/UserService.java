package com.lan.website.service;

import com.lan.website.mbg.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    int deleteByPrimaryKey(String id);
    User selectByPrimaryKey(String id);
    List<User> selectAllUser();

    //========= 这两个只是要返回的类型不同罢了 其实后面那个应该命名或返回类型写成boolean的 但因为是借鉴别人的代码 暂时不改了
    User selectLoginUser(Map map);
    int selectUser(Map map);
    //=======
    int selectUser(String username);
    int insertSelective(User record);
    int updateByPrimaryKeySelective(User record);
}
