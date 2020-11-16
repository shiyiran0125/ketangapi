package com.cqut.stu.pai.service;

import com.cqut.stu.pai.entity.JsonData;
import com.cqut.stu.pai.entity.Student;


public interface StudentService {
    //注册
    public JsonData register(Student student);

}
