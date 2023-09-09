package com.lan.website.service.impl;

import com.lan.website.mbg.mapper.MessageMapper;
import com.lan.website.mbg.model.Message;
import com.lan.website.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public int delete(Message record) {
        return messageMapper.delete(record);
    }

    @Override
    public Message select(Message record) {

        return messageMapper.select(record);
    }

    @Override
    public int insert(Message record) {
        return messageMapper.insert(record);
    }

    @Override
    public List<Message> selectMsgById(String userid) {
        return messageMapper.selectMy(userid);
    }
}
