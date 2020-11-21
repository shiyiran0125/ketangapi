package com.cqut.stu.pai.entity.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 石益然
 * @program: pai
 * @description: 包装一个作业下一个学生和作业的信息
 * @date 2020-11-20 16:52:06
 */
@ApiModel
@Component
public class StuHomework {
    private String S_id;
    private String S_name;
    private Integer Score;
    private Integer fullScore;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date Submit_time;
    private Integer Correct_count;
    private Byte State;

    @JsonProperty("S_id")
    public String getS_id() {
        return S_id;
    }

    public void setS_id(String s_id) {
        S_id = s_id;
    }

    @JsonProperty("S_name")
    public String getS_name() {
        return S_name;
    }

    public void setS_name(String s_name) {
        S_name = s_name;
    }

    @JsonProperty("Score")
    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer score) {
        Score = score;
    }

    @JsonProperty("fullScore")
    public Integer getFullScore() {
        return fullScore;
    }

    public void setFullScore(Integer fullScore) {
        this.fullScore = fullScore;
    }

    @JsonProperty("Submit_time")
    public Date getSubmit_time() {
        return Submit_time;
    }

    public void setSubmit_time(Date submit_time) {
        Submit_time = submit_time;
    }

    @JsonProperty("Correct_count")
    public Integer getCorrect_count() {
        return Correct_count;
    }

    public void setCorrect_count(Integer correct_count) {
        Correct_count = correct_count;
    }

    @JsonProperty("State")
    public Byte getState() {
        return State;
    }

    public void setState(Byte state) {
        State = state;
    }
}