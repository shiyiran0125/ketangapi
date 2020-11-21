package com.cqut.stu.pai.config;

import com.cqut.stu.pai.entity.Role;
import com.cqut.stu.pai.entity.Teacher;
import com.cqut.stu.pai.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 石益然
 * @program: pai
 * @description: 校验超级管理员
 * @date 2020-11-14 17:37:09
 */
public class AdminAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    AdminService adminService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!authentication.getName().equals("root")|| !authentication.getCredentials().equals("123456")){
            return null;
        }else {
            Teacher teacher = new Teacher("root","",true);
            List<Role> list = new ArrayList<>();
            list.add(new Role(1,"ADMIN","超级管理员"));
            teacher.setRoles(list);
            Collection<? extends GrantedAuthority> authorities = teacher.getAuthorities();
            return new UsernamePasswordAuthenticationToken(teacher,"123456",authorities);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}