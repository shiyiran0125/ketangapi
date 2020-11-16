package com.cqut.stu.pai.controller;

import com.cqut.stu.pai.entity.JsonData;
import com.cqut.stu.pai.entity.Teacher;
import com.cqut.stu.pai.service.TeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 石益然
 * @program: pai
 * @description: 教师端接口
 * @date 2020-11-15 17:14:27
 */
@RestController
@Api(tags = "教师端接口")
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @PostMapping("/register")
    public JsonData register(@RequestBody Teacher teacher){
        return teacherService.register(teacher);
    }
}