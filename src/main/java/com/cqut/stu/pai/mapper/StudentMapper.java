package com.cqut.stu.pai.mapper;

import com.cqut.stu.pai.entity.JsonData;
import com.cqut.stu.pai.entity.Student;
import com.cqut.stu.pai.entity.response.CourseWithStudent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    //注册
    public Integer haveExistInTea(String username);
    public void register(Student student);

    //登录
    Student loadUserByUsername(String username);

    //获取课程列表
    public List<CourseWithStudent> getCourseList(Integer userId);

    //根据加课码获取课程/加课
    public Integer getCountOfCourse(String C_code);
    public void addStuCou(String C_code, Integer userId,String Time);
}
