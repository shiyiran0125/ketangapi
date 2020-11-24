package com.cqut.stu.pai.service;

import com.cqut.stu.pai.entity.Course;
import com.cqut.stu.pai.entity.Homework;
import com.cqut.stu.pai.entity.JsonData;
import com.cqut.stu.pai.entity.Teacher;
import com.cqut.stu.pai.entity.response.MenberOfStudent;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface TeacherService {
    //注册
    public JsonData register(Teacher teacher);

    //获取课程列表
    public JsonData getCourseList(String username);

    //添加课程
    public JsonData addCourse(Course course);

    //删除课程
    public JsonData deleteCourse(String C_code);

    //修改课程
    public JsonData modifyCourse(Course course);

    //置顶与取消
    public JsonData topCourse(Integer Top, String C_code);

    //加课码启用与停用
    public JsonData courseCode( Integer Use, String C_code);

    //归档与取消
    public JsonData guiDangCourse(Integer Archive, String C_code);

    //加课
    public JsonData joinCourse(String C_code);

    //获取课程的所有作业
    public JsonData getHomeworkList(String C_code);

    //发布作业
    public JsonData addHomework(MultipartFile file, Homework homework, HttpServletRequest request);

    //修改作业
    public JsonData modifyHomework(Homework homework);

    //删除作业
    public JsonData deleteHomework(String H_id);

    //得到某作业下所有学生的提交作业
    public JsonData getStuHomework( String H_id);

    //根据课程ID获取某课程的所有老师和学生
    public JsonData getStuTea(String C_code);

    //修改学生信息
    public JsonData modifyStudent(MenberOfStudent student);

    //删除课程下的学生
    public JsonData deleteStudent(String username,String C_code);

    //批改作业
    public JsonData modifyScore(Integer H_id, String sid,Integer Score);
}
