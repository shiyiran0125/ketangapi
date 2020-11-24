package com.cqut.stu.pai.mapper;

import com.cqut.stu.pai.entity.Homework;
import com.cqut.stu.pai.entity.JsonData;
import com.cqut.stu.pai.entity.Student;
import com.cqut.stu.pai.entity.StudentWithHomework;
import com.cqut.stu.pai.entity.response.CourseWithStudent;
import com.cqut.stu.pai.entity.response.MenberOfStudent;
import com.cqut.stu.pai.entity.response.MenberOfTeacher;
import com.cqut.stu.pai.entity.response.StudentAllHomeworkInCourse;
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
    public List<Integer> getAllHomeIdOfCourse(String C_code);
    public void insertToStuWor(List<StudentWithHomework> list);
    public void updateUnSubmitted(List<Integer> list);

    //置顶与取消
    public void topCourse(Integer S_id,String C_code);
    public void notTopCourse(Integer S_id,String C_code);

    //归档课程
    public void guiDangCourseUse(Integer S_id,String C_code);
    public void guiDangCourseStop(Integer S_id,String C_code);

    //删除课程下的学生
    public void deleteStuHome(String username,String C_code);
    public void deleteStudent(String username,String C_code);

    //获取课程的所有作业
    public List<StudentAllHomeworkInCourse> getHomeworkList(String C_code, Integer S_id);

    //提交作业
    public void commitHomework(String Answer,Integer H_id,String Submit_time,Integer S_id);
    public void modifySubmit(Integer H_id);

    //根据课程ID获取某课程的所有老师
    public List<MenberOfTeacher> getTeaMen(String C_code);
    //根据课程ID获取某课程的所有学生
    public List<MenberOfStudent> getStuMen(String C_code);

    //更新作业表
    public void updateHomework(String C_code);
}
