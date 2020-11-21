package com.cqut.stu.pai.service.impl;

import com.cqut.stu.pai.entity.JsonData;
import com.cqut.stu.pai.entity.Student;
import com.cqut.stu.pai.entity.Teacher;
import com.cqut.stu.pai.entity.response.CourseWithStudent;
import com.cqut.stu.pai.entity.response.CourseWithTeacher;
import com.cqut.stu.pai.mapper.StudentMapper;
import com.cqut.stu.pai.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author 石益然
 * @program: pai
 * @description: 学生业务类
 * @date 2020-11-14 17:48:16
 */
@Service
public class StudentServiceImpl implements StudentService, UserDetailsService {
    @Autowired
    StudentMapper studentMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return studentMapper.loadUserByUsername(username);
    }

    @Transactional
    @Override
    public JsonData register(Student student) {
        Student OldStudent = studentMapper.loadUserByUsername(student.getUsername());
        int haveExist = studentMapper.haveExistInTea(student.getUsername());
        if (OldStudent == null && haveExist==0) {
            studentMapper.register(student);
            student.setPassword(null);
            JsonData jsonData = JsonData.success("注册成功",student);
            return jsonData;
        }else {
            JsonData jsonData = JsonData.Error("该账号已经存在");
            return jsonData;
        }
    }

    @Override
    public JsonData getCourseList(String username) {

        Student user = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<CourseWithStudent> list = studentMapper.getCourseList(user.getId());
        List<CourseWithStudent> topList = new ArrayList<>();
        List<CourseWithStudent> dList = new ArrayList<>();
        List<Map<String,String>> ArList = new ArrayList<>();
        for (CourseWithStudent ct:list
        ) {
            if (ct.getArchive()==1){
                Map<String,String> map = new HashMap<>();
                map.put("C_code",ct.getC_code());
                map.put("C_name",ct.getC_name());
                ArList.add(map);
            } else if (ct.getTop()==0){
                dList.add(ct);
            }else {
                topList.add(ct);
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("topCourse",topList);
        map.put("dCourse",dList);
        map.put("guidangCourse",ArList);
        JsonData jsonData = new JsonData(200,map,"success");
        return jsonData;
    }

    @Transactional
    @Override
    public JsonData joinCourse(String C_code) {
        Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int count = studentMapper.getCountOfCourse(C_code);
        JsonData jsonData =null;
        if (count<0){
            jsonData = new JsonData(404,"","error");
        }else {
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            studentMapper.addStuCou(C_code,student.getId(),formatter.format(localDateTime));
            jsonData = new JsonData(200,"","success");
        }
        return jsonData;
    }
}