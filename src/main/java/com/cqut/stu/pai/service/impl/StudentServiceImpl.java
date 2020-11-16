package com.cqut.stu.pai.service.impl;

import com.cqut.stu.pai.entity.JsonData;
import com.cqut.stu.pai.entity.Student;
import com.cqut.stu.pai.mapper.StudentMapper;
import com.cqut.stu.pai.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        if (OldStudent == null) {
            studentMapper.register(student);
            student.setPassword(null);
            JsonData jsonData = JsonData.success("注册成功",student);
            return jsonData;
        }else {
            JsonData jsonData = JsonData.Error("该账号已经存在");
            return jsonData;
        }
    }
}