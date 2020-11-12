package com.cqut.stu.pai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        auth.inMemoryAuthentication()
                .withUser("root").password("123456").roles("ADMIN")
                .and()
                .withUser("liy").password("654321").roles("USER");
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
                .antMatchers("/test/login")
                .permitAll()
                .antMatchers("/test/**")
                .hasRole("ADMIN")
                .antMatchers(("/user/**"))
                .access("hasAnyAuthority('ADMIN','USER')")
                .antMatchers("/webjars/springfox-swagger-ui","/webjars/springfox-swagger-ui/**").permitAll()//过滤Swagger相关路径
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .permitAll()
                .and()
                .csrf()
                .disable();
        //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
