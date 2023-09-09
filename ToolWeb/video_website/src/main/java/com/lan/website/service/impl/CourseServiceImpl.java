package com.lan.website.service.impl;

import com.lan.website.mbg.mapper.CourseMapper;
import com.lan.website.mbg.model.Course;
import com.lan.website.service.CourseService;
import com.lan.website.util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<Course> selectAllCourse() {
        return courseMapper.selectAllCourse();
    }

    @Override
    public Course selectByPrimaryKey(int id)
    {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Course record)
    {
        return courseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Course> courseSearch(String search) {
        return courseMapper.coursesearch(search);
    }

    @Override
    public List<Course> freeCourse() {
        return courseMapper.freecourse();
    }

    @Override
    public List<Course> selectVipCourse() {

        return courseMapper.vipcourse();
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return courseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Course selectLastCourse() {
        return courseMapper.selectlastcourse();
    }

    @Override
    public int saveCourse(HttpServletRequest req)
    {
        int maxId = courseMapper.selectlastcourse().getId();
        Course course = (Course) UploadFile.uploadFile(String.valueOf(maxId),req);
        if(course.getId()==0)
        {
            course.setId(maxId+1);
            courseMapper.insertSelective(course);
        }else {
            courseMapper.updateByPrimaryKeySelective(course);
        }
        return 0;
    }
}
