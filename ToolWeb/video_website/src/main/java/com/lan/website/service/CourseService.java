package com.lan.website.service;

import com.lan.website.mbg.model.Course;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CourseService {
    public List<Course> selectAllCourse();
    Course selectByPrimaryKey(int id);
    int updateByPrimaryKeySelective(Course record);
    List<Course> courseSearch(String search);
    List<Course> freeCourse();
    List<Course> selectVipCourse(); //select免费的课堂
    int deleteByPrimaryKey(String id);
    Course selectLastCourse();
    int saveCourse(HttpServletRequest req);
}
