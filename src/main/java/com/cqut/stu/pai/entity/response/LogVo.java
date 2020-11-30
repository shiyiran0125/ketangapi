package com.cqut.stu.pai.entity.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import sun.rmi.runtime.Log;

import java.util.Date;

/**
 * @author 石益然
 * @program: pai
 * @description: 日志对象
 * @date 2020-11-28 14:34:38
 */
public class LogVo {
    private Integer id;
    private String  username;
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date    time;
    private String  type;
    private Boolean state;

    public LogVo(){}

    public LogVo(String username, Date time, String type, Boolean state) {
        this.username = username;
        this.time = time;
        this.type = type;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}