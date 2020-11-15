package com.cqut.stu.pai.util;

import com.cqut.stu.pai.entity.Role;
import com.cqut.stu.pai.entity.Student;
import com.cqut.stu.pai.entity.Teacher;
import com.cqut.stu.pai.service.impl.StudentServiceImpl;
import com.cqut.stu.pai.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 石益然
 * @program: pai
 * @description: 校验学生登录
 * @date 2020-11-14 17:31:07
 */
public class StudentAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        StudentServiceImpl studentService = SpringContextUtil.getContext().getBean(StudentServiceImpl.class);
        String username = authentication.getName();
        String password = (String)authentication.getCredentials();
        //判断用户名是否存在和密码是否正确
        Student student = (Student) studentService.loadUserByUsername(username);
        if (student == null){
           throw new BadCredentialsException("账号不存在");
        }

        //判断密码是否正确
        //用编码器加盐将密码加密后比较
        //String encodPass = Bcript.encode(password,盐)
        //
        if (!student.getPassword().equals(password)){
            throw new BadCredentialsException("密码不正确");
        }
        List<Role> list = new ArrayList<>();
        list.add(new Role(3,"STUDENT","学生"));
        student.setRoles(list);
        Collection<? extends GrantedAuthority> authorities = student.getAuthorities();
        //构建返回的用户登录成功的token
        return new UsernamePasswordAuthenticationToken(student,password,authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}