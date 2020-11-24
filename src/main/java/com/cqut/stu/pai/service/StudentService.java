package com.cqut.stu.pai.service;

import com.cqut.stu.pai.entity.JsonData;
import com.cqut.stu.pai.entity.Student;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


public interface StudentService {
    //注册
    public JsonData register(Student student);

    //获取课程列表
    public JsonData getCourseList(String username);

    //加课
    public JsonData joinCourse(String C_code);

    //置顶与取消
    public JsonData topCourse(Integer Top, String C_code);

    //归档与取消
    public JsonData guiDangCourse(Integer Archive, String C_code);

    //删除课程下的学生
    public JsonData deleteStudent(String username,String C_code);

    //获取课程的所有作业
    public JsonData getHomeworkList(String C_code);

    //提交作业
    public JsonData commitHomework(MultipartFile file,Integer H_id);

    //根据课程ID获取某课程的所有老师和学生
    public JsonData getStuTea(String C_code);
}
