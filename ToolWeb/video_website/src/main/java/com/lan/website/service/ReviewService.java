package com.lan.website.service;

import com.lan.website.mbg.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> select(int courseid);
    int delete(Review review);
    int insert(Review review);
    String avgLable(int courseid); //返回String形式的评价
    List<Review> selectByUserId(String username);
    int updateByPrimaryKeySelective(List<Review> reviews);
}
