package com.cqut.stu.pai.entity.response;

import org.springframework.stereotype.Component;

/**
 * @author 石益然
 * @program: pai
 * @description: 包装课程教师成员
 * @date 2020-11-21 15:38:18
 */
@Component
public class MenberOfTeacher {
    private String name;
    private String username;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}