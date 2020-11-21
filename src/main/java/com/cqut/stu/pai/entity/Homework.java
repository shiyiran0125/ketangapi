package com.cqut.stu.pai.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 石益然
 * @program: pai
 * @description: 封装作业类
 * @date 2020-11-16 20:46:25
 */
@Component
public class Homework {
    /*作业：作业ID,加课码,类型（小组、个人）,发布日期，标题，内容，附件（服务器上的文件名称），已批改人数，未批改人数，已交人数，未交人数,截止时间,满分分值*/
    private Integer H_id;
    private String C_code;
    private String Type;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date Date_issue;
    private String H_name;
    private String Content;
    private String Annex;
    private Integer Corrected;
    private Integer Uncorrected;
    private Integer Submitted;
    private Integer Unsubmitted;
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date  Deadline;
    private Short Score;

    @JsonProperty("H_id")
    public Integer getH_id() {
        return H_id;
    }

    public void setH_id(Integer h_id) {
        H_id = h_id;
    }
    @JsonProperty("C_code")
    public String getC_code() {
        return C_code;
    }

    public void setC_code(String c_code) {
        C_code = c_code;
    }
    @JsonProperty("Type")
    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
    @JsonProperty("Date_issue")
    public Date getDate_issue() {
        return Date_issue;
    }

    public void setDate_issue(Date date_issue) {
        Date_issue = date_issue;
    }
    @JsonProperty("H_name")
    public String getH_name() {
        return H_name;
    }

    public void setH_name(String h_name) {
        H_name = h_name;
    }
    @JsonProperty("Content")
    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
    @JsonProperty("Annex")
    public String getAnnex() {
        return Annex;
    }

    public void setAnnex(String annex) {
        Annex = annex;
    }
    @JsonProperty("Corrected")
    public Integer getCorrected() {
        return Corrected;
    }

    public void setCorrected(Integer corrected) {
        Corrected = corrected;
    }
    @JsonProperty("Uncorrected")
    public Integer getUncorrected() {
        return Uncorrected;
    }

    public void setUncorrected(Integer uncorrected) {
        Uncorrected = uncorrected;
    }
    @JsonProperty("Submitted")
    public Integer getSubmitted() {
        return Submitted;
    }

    public void setSubmitted(Integer submitted) {
        Submitted = submitted;
    }
    @JsonProperty("Unsubmitted")
    public Integer getUnsubmitted() {
        return Unsubmitted;
    }

    public void setUnsubmitted(Integer unsubmitted) {
        Unsubmitted = unsubmitted;
    }
    @JsonProperty("Deadline")
    public Date getDeadline() {
        return Deadline;
    }

    public void setDeadline(Date deadline) {
        Deadline = deadline;
    }
    @JsonProperty("Score")
    public Short getScore() {
        return Score;
    }

    public void setScore(Short score) {
        Score = score;
    }
}