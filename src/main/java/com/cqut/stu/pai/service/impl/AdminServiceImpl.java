package com.cqut.stu.pai.service.impl;

import com.cqut.stu.pai.service.AdminService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 石益然
 * @program: pai
 * @description: 超级管理员业务类
 * @date 2020-11-14 17:47:18
 */
@Service
public class AdminServiceImpl implements AdminService, UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}