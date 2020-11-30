package com.cqut.stu.pai.controller;

import com.cqut.stu.pai.entity.manage.LogObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * @author 石益然
 * @program: pai
 * @description: 超级管理员端接口
 * @date 2020-11-15 17:15:26
 */
@Controller
@Api(tags = "超级管理员端接口")
@RequestMapping("/admin")
public class AdminController {
    @ApiOperation(value="超级管理员",notes = "管理用户，用户信息")
    @GetMapping("/login")
    public String toIndex(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        ArrayList<LogObject> list = new ArrayList<>();
        list.add(new LogObject("Async：简洁优雅的异步之道","在异步处理方案中，目前最为简洁优雅的便是async函数（以下简称A函数）。","www.baidu.com"));
        list.add(new LogObject("H5 前端性能测试实践","H5 页面发版灵活，轻量，又具有跨平台的特性，在业务上有很多应用场景。","www.baidu.com"));
        list.add(new LogObject("学习Python的建议","Python是最容易入门的编程语言。","www.baidu.com"));
        model.addAttribute("articleList",list);
        return "index";
    }

    @GetMapping("/teacherInfo")
    public String getTeacherInfo() {
        return "teacherInfo";
    }

    @GetMapping("/studentInfo")
    public String getStudentInfo() {
        return "studentInfo";
    }

    @GetMapping("/teacherLog")
    public String getTeacherLog() {
        return "teacherLog";
    }

    @GetMapping("/studentLog")
    public String getStudentLog() {
        return "studentLog";
    }



}