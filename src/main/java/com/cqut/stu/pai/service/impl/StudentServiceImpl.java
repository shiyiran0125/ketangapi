package com.cqut.stu.pai.service.impl;

import com.cqut.stu.pai.service.StudentService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 石益然
 * @program: pai
 * @description: 学生业务类
 * @date 2020-11-14 17:48:16
 */
@Service
public class StudentServiceImpl implements StudentService, UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}