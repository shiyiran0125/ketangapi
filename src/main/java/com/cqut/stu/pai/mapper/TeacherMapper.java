package com.cqut.stu.pai.mapper;

import com.cqut.stu.pai.entity.*;
import com.cqut.stu.pai.entity.response.CourseWithTeacher;
import com.cqut.stu.pai.entity.response.MenberOfStudent;
import com.cqut.stu.pai.entity.response.MenberOfTeacher;
import com.cqut.stu.pai.entity.response.StuHomework;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {
    //注册
    public Integer haveExistInStu(String username);
    public void register(Teacher teacher);

    //登录
    Teacher loadUserByUsername(String username);

    //获取课程列表
    public List<CourseWithTeacher> getCourseList(Integer userId);

    //根据加课码获取课程
    public Integer getCountOfCourse(String C_code);

    //添加课程
    public void addCourse(Course course);
    //添加课程码记录到教师课程表,同时用与加课
    public void addTeaCou(String C_code, Integer userId);

    //修改课程
    public void modifyCourse(Course course);

    //删除课程
    public void deleteCourse(String C_code);
    public void deleteCt(String C_code);

    //置顶与取消
    public void topCourse(Integer T_id,String C_code);
    public void notTopCourse(Integer T_id,String C_code);

    //加课码启用与停用
    public void courseCodeUse( String C_code);
    public void courseCodeStop( String C_code);

    //归档课程
    public void guiDangCourseUse(Integer T_id,String C_code);
    public void guiDangCourseStop(Integer T_id,String C_code);

    //获取课程的所有作业
    public List<Homework> getHomeworkList(String C_code);

    //添加作业
    public List<Integer> getAllStuInCourse(String C_code);
    public void addHomework(Homework homework);
    public void insertToStuWor(List<StudentWithHomework> list);

    //修改作业
    public void modifyHomework(Homework homework);

    //删除作业
    public void deleteHomework(String H_id);
    public void deleteStuHom(String H_id);

    //得到某作业下所有学生的提交作业
    public List<StuHomework> getStuHomework(String H_id);

    //根据课程ID获取某课程的所有老师
    public List<MenberOfTeacher> getTeaMen(String C_code);
    //根据课程ID获取某课程的所有学生
    public List<MenberOfStudent> getStuMen(String C_code);

    //修改学生信息
    public Integer getStuBySid(String sid,String username);
    public void modifyStudent(MenberOfStudent student);

    //删除课程下的学生
    public void deleteStuHome(String username,String C_code);
    public void deleteStudent(String username,String C_code);

    //更新作业表
    public void updateHomework(String C_code);

    //
    public void modifyScore(Integer H_id, String sid,Integer Score);
    public String getCodeByHid(Integer H_id);
}
