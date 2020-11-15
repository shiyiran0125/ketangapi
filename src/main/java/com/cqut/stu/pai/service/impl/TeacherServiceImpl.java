package com.cqut.stu.pai.service.impl;

import com.cqut.stu.pai.entity.Teacher;
import com.cqut.stu.pai.service.TeacherService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 石益然
 * @program: pai
 * @description: 教师业务类
 * @date 2020-11-14 17:49:00
 */
@Service
public class TeacherServiceImpl implements TeacherService, UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}