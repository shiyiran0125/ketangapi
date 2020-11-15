package com.cqut.stu.pai.config;

import com.cqut.stu.pai.entity.JsonData;
import com.cqut.stu.pai.entity.Student;
import com.cqut.stu.pai.entity.Teacher;
import com.cqut.stu.pai.util.AdminAuthenticationProvider;
import com.cqut.stu.pai.util.StudentAuthenticationProvider;
import com.cqut.stu.pai.util.TeacherAuthenticationProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 石益然
 * @program: ketangpai
 * @description: 身份认证和权限校验安全配置类
 * @date 2020-11-10 20:04:01
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * @describe: 返回一个密码编码器
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    /**
     * @param auth
     * @return void
     * @describe: 全局安全配置
     * @date 2020/11/10 20:22
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(new AdminAuthenticationProvider())
                .authenticationProvider(new TeacherAuthenticationProvider())
                .authenticationProvider(new StudentAuthenticationProvider());
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        //过滤路径
        web.ignoring()
                .antMatchers("/static/**")
                .antMatchers("/swagger-ui.html")//过滤Swagger相关路径
                .antMatchers("/v2/**")
                .antMatchers("/swagger-resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/login")
                .permitAll()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .antMatchers(("/student/**"))
                .access("hasAnyAuthority('ADMIN','STUDENT')")
                .antMatchers(("/teacher/**"))
                .access("hasAnyAuthority('ADMIN','TEACHER')")
                .antMatchers("/webjars/springfox-swagger-ui","/webjars/springfox-swagger-ui/**").permitAll()//过滤Swagger相关路径
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        Object user = authentication.getPrincipal();
                        String name = "";
                        if (user instanceof Student){
                            ((Student) user).setPassword(null);
                            name = ((Student) user).getUsername();
                        }else{
                            ((Teacher) user).setPassword(null);
                            name = ((Teacher) user).getUsername();
                        }
//                        HttpSession session = request.getSession();
//                        session.setAttribute("loinUser",user.getId());
//                        redisTemplate.opsForValue().set("loginUser:" + user.getId(), session.getId());
                        JsonData ok = new JsonData(200,user,"登录成功！"+ name);
                        String s = new ObjectMapper().writeValueAsString(ok);
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        JsonData error = new JsonData(200,"登录失败");
                        if(exception instanceof BadCredentialsException){
                            error.setMsg("用户名或密码错误，请重新输入");
                        } else if(exception instanceof DisabledException) {
                            error.setMsg("账户被锁定");
                        }
                        out.write(new ObjectMapper().writeValueAsString(error));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        JsonData respBean = new JsonData(200,"退出成功");
                        out.write(new ObjectMapper().writeValueAsString(respBean));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .csrf()
                .disable();
                /*.exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    //未认证时访问不要重定向返回JSON格式数据
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        response.setStatus(401);
                        PrintWriter out = response.getWriter();
                        JsonData respBean = JsonData.Error("访问失败");
                        if(authException instanceof InsufficientAuthenticationException){
                            respBean.setMsg("未登录无法访问");
                        }
                        out.write(new ObjectMapper().writeValueAsString(respBean));
                        out.flush();
                        out.close();
                    }
                })*/
        //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
