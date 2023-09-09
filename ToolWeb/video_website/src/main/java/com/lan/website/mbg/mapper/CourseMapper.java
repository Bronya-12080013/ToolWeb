package com.lan.website.mbg.mapper;


import com.lan.website.mbg.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 
 * @author ccnoobs-杨祺晖
 *
 */
@Component
public interface CourseMapper {
	public List<Course> coursesearch(String scarch);
	public List<Course> selectAllCourse();
	public List<Course> freecourse(); //select免费的课堂
	public List<Course> vipcourse();
	Course selectlastcourse();
    int deleteByPrimaryKey(String id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}