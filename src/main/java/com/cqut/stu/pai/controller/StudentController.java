package com.cqut.stu.pai.controller;

import com.cqut.stu.pai.entity.JsonData;
import com.cqut.stu.pai.entity.Student;
import com.cqut.stu.pai.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @ApiOperation(value="注册用户",notes = "注册用户，传入用户信息")
    @PostMapping("/register")
    public JsonData register(@RequestBody Student student){
         return studentService.register(student);
    }

    /**
     * @param username
     * @return com.cqut.stu.pai.entity.JsonData
     * @describe: 获取用户课程列表
     * @date 2020/11/16 20:24
     */
    @ApiOperation(value="获取课程列表",notes = "返回该教师的所有课程，包括置顶课程，非置顶课程，归档课程")
    @ApiImplicitParam(name = "username",value = "用户名",required = true)
    @GetMapping("/GetCourseList/{username}")
    public JsonData getCourseList(@PathVariable String username){
        return studentService.getCourseList(username);
    }

    @ApiOperation(value="加入课程",notes = "根据加课码加入课程")
    @ApiImplicitParam(name = "C_code",value = "课程码",required = true)
    @PostMapping("/JoinCourse")
    public JsonData joinCourse(@RequestBody Map<String,String> C_code){
        return studentService.joinCourse(C_code.get("C_code"));
    }
}