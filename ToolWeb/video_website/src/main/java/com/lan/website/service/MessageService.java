package com.lan.website.service;

import com.lan.website.mbg.model.Message;

import java.util.List;

public interface MessageService {
    int delete(Message record);
    Message select(Message record);
    int insert(Message record);
    List<Message> selectMsgById(String userid);

}
