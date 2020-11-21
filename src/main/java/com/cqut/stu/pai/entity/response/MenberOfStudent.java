package com.cqut.stu.pai.entity.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 石益然
 * @program: pai
 * @description: 包装课程学生成员
 * @date 2020-11-21 15:39:58
 */
@Component
public class MenberOfStudent {
    private String sid;
    private String name;
    private String username;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date   date;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}