package com.cqut.stu.pai.service;

import com.cqut.stu.pai.entity.JsonData;
import com.cqut.stu.pai.entity.Student;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;


public interface StudentService {
    //注册
    public JsonData register(Student student);

    //获取课程列表
    public JsonData getCourseList(String username);

    //加课
    public JsonData joinCourse(String C_code);

}
