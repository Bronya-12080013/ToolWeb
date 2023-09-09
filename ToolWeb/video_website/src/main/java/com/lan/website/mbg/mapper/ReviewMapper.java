package com.lan.website.mbg.mapper;


import com.lan.website.mbg.model.Review;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReviewMapper {
 List<Review> select(int courseid);
 int delete(Review review);
 int insert(Review review);
 List<Review> selectbyuserid(String username);
 int updateByPrimaryKeySelective(Review review);
}
