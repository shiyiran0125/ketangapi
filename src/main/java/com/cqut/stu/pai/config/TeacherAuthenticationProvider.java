package com.cqut.stu.pai.config;

import com.cqut.stu.pai.entity.Role;
import com.cqut.stu.pai.entity.Teacher;
import com.cqut.stu.pai.service.TeacherService;
import com.cqut.stu.pai.service.impl.TeacherServiceImpl;
import com.cqut.stu.pai.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 石益然
 * @program: pai
 * @description: 检验教师登录
 * @date 2020-11-14 17:18:50
 */
public class TeacherAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TeacherServiceImpl teacherService = SpringContextUtil.getContext().getBean(TeacherServiceImpl.class);
        String username = authentication.getName();
        String password = (String)authentication.getCredentials();
        //判断用户名是否存在和密码是否正确
        Teacher teacher = (Teacher) teacherService.loadUserByUsername(username);
        if (teacher == null){
            return null;
        }

        //判断密码是否正确
        //用编码器加盐将密码加密后比较
        //String encodPass = Bcript.encode(password,盐)
        //



        if (!teacher.getPassword().equals(password)){
            throw new BadCredentialsException("密码不正确");
        }
        List<Role> list = new ArrayList<>();
        list.add(new Role(2,"TEACHER","教师"));
        teacher.setRoles(list);
        ArrayList<GrantedAuthority> authorities = new ArrayList<>()/*teacher.getAuthorities()*/;
        authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
        //构建返回的用户登录成功的token
        return new UsernamePasswordAuthenticationToken(teacher,password,authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}