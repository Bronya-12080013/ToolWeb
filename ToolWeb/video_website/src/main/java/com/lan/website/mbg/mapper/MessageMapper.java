package com.lan.website.mbg.mapper;


import com.lan.website.mbg.model.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MessageMapper {
	 int delete(Message record);
      Message select(Message record);
	    int insert(Message record);
      List<Message> selectMy(String userid);
}
