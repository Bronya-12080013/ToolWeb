package com.lan.website.service.impl;

import com.lan.website.mbg.mapper.ReviewMapper;
import com.lan.website.mbg.model.Review;
import com.lan.website.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewMapper reviewMapper;

    @Override
    public List<Review> select(int courseId) {
        return reviewMapper.select(courseId);
    }

    @Override
    public int delete(Review review) {
        return reviewMapper.delete(review);
    }

    @Override
    public int insert(Review review) {

        return reviewMapper.insert(review);
    }

    //返回一个课程的平均评价/分数
    @Override
    public String avgLable(int courseId) {
        int a=0;
        List<Review> reviews = reviewMapper.select(courseId);
        for(Review review:reviews){
            a+=review.getLable();
        }
        a/=reviews.size()+1;
        if(a<1.5){
            return "一般";
        }else
        if(a<2.5){
            return "还行";
        }else
        if(a<3.5){
            return "不错";
        }else
            return "非常好";
    }

    @Override
    public List<Review> selectByUserId(String username){
        return reviewMapper.selectbyuserid(username);
    }

    @Override
    public int updateByPrimaryKeySelective(List<Review> reviews) {
        for(Review review:reviews){
            reviewMapper.updateByPrimaryKeySelective(review);
        }
        return 0;
    }
}
