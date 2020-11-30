package com.cqut.stu.pai.entity.response;

import com.cqut.stu.pai.entity.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author 石益然
 * @program: pai
 * @description: 教师
 * @date 2020-11-26 22:59:06
 */
@ApiModel
public class TeacherVo {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "用户姓名")
    private String name;
    @ApiModelProperty(value = "账号")
    private String username;
    @ApiModelProperty(value = "学校")
    private String school;
    @ApiModelProperty(value = "账号状态")
    @JsonProperty("accountNonLocked")
    private Boolean accountNonLocked;
    @ApiModelProperty(value = "角色")
    private String role;
    @ApiModelProperty(value = "加课时间")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date joinTime;

    public TeacherVo(){}

    public TeacherVo(Integer id, String name, String username,  String school, Boolean enabled, String role, Date joinTime) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.school = school;
        accountNonLocked = enabled;
        this.role = role;
        this.joinTime = joinTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean enabled) {
        accountNonLocked = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }
}