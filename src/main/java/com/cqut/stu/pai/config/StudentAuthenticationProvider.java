package com.cqut.stu.pai.config;

import com.cqut.stu.pai.entity.Role;
import com.cqut.stu.pai.entity.Student;
import com.cqut.stu.pai.entity.Teacher;
import com.cqut.stu.pai.entity.response.LogVo;
import com.cqut.stu.pai.service.LogService;
import com.cqut.stu.pai.service.impl.LogServiceImpl;
import com.cqut.stu.pai.service.impl.StudentServiceImpl;
import com.cqut.stu.pai.service.impl.TeacherServiceImpl;
import com.cqut.stu.pai.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

        if (!student.isAccountNonLocked()){
            throw new DisabledException("账号被锁定");
        }

        List<Role> list = new ArrayList<>();
        list.add(new Role(3,"STUDENT","学生"));
        student.setRoles(list);
        Collection<GrantedAuthority> authorities = new ArrayList<>()/*student.getAuthorities()*/;
        authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
        //构建返回的用户登录成功的token
        LogVo logVo = new LogVo(student.getUsername(),new Date(),"登录",true);
        LogServiceImpl logService = SpringContextUtil.getContext().getBean(LogServiceImpl.class);
        logService.studentLog(logVo);
        return new UsernamePasswordAuthenticationToken(student,password,authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}