package com.cqut.stu.pai.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 石益然
 * @program: pai
 * @description: 封装教师和课程信息
 * @date 2020-11-17 17:12:39
 */
@ApiModel
@Component
public class CourseWithTeacher implements Serializable {
    /*课程，归档记录，置顶记录，最新作业ID和名称*/

    private String C_code;

    private String C_name;

    private String C_cname;

    private String C_year;

    private String C_term;

    private Byte Codeuse;

    private String C_info;

    private String T_name;

    private Integer C_menber;

    private Byte Archive;

    private Byte Top;

    private Integer H_id;

    private String H_name;

    private Date Date_issue;
    @JsonProperty("C_code")
    public String getC_code() {
        return C_code;
    }

    public void setC_code(String c_code) {
        C_code = c_code;
    }
    @JsonProperty("C_name")
    public String getC_name() {
        return C_name;
    }

    public void setC_name(String c_name) {
        C_name = c_name;
    }
    @JsonProperty("C_cname")
    public String getC_cname() {
        return C_cname;
    }

    public void setC_cname(String c_cname) {
        C_cname = c_cname;
    }
    @JsonProperty("C_year")
    public String getC_year() {
        return C_year;
    }

    public void setC_year(String c_year) {
        C_year = c_year;
    }
    @JsonProperty("C_term")
    public String getC_term() {
        return C_term;
    }

    public void setC_term(String c_term) {
        C_term = c_term;
    }
    @JsonProperty("Codeuse")
    public Byte getCodeuse() {
        return Codeuse;
    }

    public void setCodeuse(Byte codeuse) {
        Codeuse = codeuse;
    }
    @JsonProperty("C_info")
    public String getC_info() {
        return C_info;
    }

    public void setC_info(String c_info) {
        C_info = c_info;
    }
    @JsonProperty("T_name")
    public String getT_name() {
        return T_name;
    }

    public void setT_name(String t_name) {
        T_name = t_name;
    }
    @JsonProperty("C_menber")
    public Integer getC_menber() {
        return C_menber;
    }

    public void setC_menber(Integer c_menber) {
        C_menber = c_menber;
    }
    @JsonProperty("Archive")
    public Byte getArchive() {
        return Archive;
    }

    public void setArchive(Byte archive) {
        Archive = archive;
    }
    @JsonProperty("Top")
    public Byte getTop() {
        return Top;
    }

    public void setTop(Byte top) {
        Top = top;
    }
    @JsonProperty("H_id")
    public Integer getH_id() {
        return H_id;
    }

    public void setH_id(Integer h_id) {
        H_id = h_id;
    }
    @JsonProperty("H_name")
    public String getH_name() {
        return H_name;
    }

    public void setH_name(String h_name) {
        H_name = h_name;
    }

    public Date getDate_issue() {
        return Date_issue;
    }

    public void setDate_issue(Date date_issue) {
        Date_issue = date_issue;
    }
}