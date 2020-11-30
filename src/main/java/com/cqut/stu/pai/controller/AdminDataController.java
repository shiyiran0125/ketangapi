package com.cqut.stu.pai.controller;

import com.cqut.stu.pai.entity.response.DataVo;
import com.cqut.stu.pai.entity.response.StudentVo;
import com.cqut.stu.pai.entity.response.TeacherVo;
import com.cqut.stu.pai.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 石益然
 * @program: pai
 * @description: 管理员数据控制器
 * @date 2020-11-26 23:07:22
 */
@RestController
@RequestMapping("/adminData")
public class AdminDataController {
    @Autowired
    AdminService adminService;
    @RequestMapping(value = "/teacherJson",method = RequestMethod.GET)
    public DataVo<TeacherVo> getTeacherJson(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return adminService.getTeacherJson(page,limit);
    }

    @RequestMapping(value = "/studentJson",method = RequestMethod.GET)
    public DataVo<StudentVo> getStudentJson(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return adminService.getStudentJson(page,limit);
    }

    @RequestMapping(value = "/teacherInfo/locked/{id}",method = RequestMethod.PUT)
    public DataVo<?> tLocked(@PathVariable("id") Integer id) {
        return adminService.locked(id);
    }

    @RequestMapping(value = "/studentInfo/locked/{id}",method = RequestMethod.PUT)
    public DataVo<?> sLocked(@PathVariable("id") Integer id) {
        return adminService.sLocked(id);
    }

    @RequestMapping(value = "/teacherLog",method = RequestMethod.GET)
    public DataVo<?> teacherLog(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return adminService.getTeacherLog(page,limit);
    }

    @RequestMapping(value = "/studentLog",method = RequestMethod.GET)
    public DataVo<?> studentLog(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return adminService.getStudentLog(page,limit);
    }


}