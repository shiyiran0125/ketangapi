package com.cqut.stu.pai.controller;

import com.cqut.stu.pai.entity.JsonData;
import com.cqut.stu.pai.entity.Student;
import com.cqut.stu.pai.service.StudentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 石益然
 * @program: pai
 * @description: 学生端接口
 * @date 2020-11-15 17:15:03
 */
@RestController
@Api(tags = "学生端接口")
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/register")
    public JsonData register(Student student){
         return studentService.register(student);
    }
}