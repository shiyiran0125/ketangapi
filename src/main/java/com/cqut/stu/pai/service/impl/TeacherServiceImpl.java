package com.cqut.stu.pai.service.impl;

import com.cqut.stu.pai.entity.JsonData;
import com.cqut.stu.pai.entity.Teacher;
import com.cqut.stu.pai.mapper.TeacherMapper;
import com.cqut.stu.pai.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 石益然
 * @program: pai
 * @description: 教师业务类
 * @date 2020-11-14 17:49:00
 */
@Service
public class TeacherServiceImpl implements TeacherService, UserDetailsService {
    @Autowired
    TeacherMapper teacherMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return teacherMapper.loadUserByUsername(username);
    }

    @Transactional
    @Override
    public JsonData register(Teacher teacher) {
        Teacher Oldteacher = teacherMapper.loadUserByUsername(teacher.getUsername());
        if (Oldteacher == null) {
            teacherMapper.register(teacher);
            teacher.setPassword(null);
            JsonData jsonData = JsonData.success("注册成功",teacher);
            return jsonData;
        }else {
            JsonData jsonData = JsonData.Error("该账号已经存在");
            return jsonData;
        }

    }
}