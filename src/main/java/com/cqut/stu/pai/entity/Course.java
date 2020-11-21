package com.cqut.stu.pai.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

/**
 * @author 石益然
 * @program: pai
 * @description: 封装课程
 * @date 2020-11-16 20:26:17
 */
@Component
@ApiModel
public class Course {
    /*课程：加课码，课程名称，班级名称，选课学年，学期，/限制/，加课码状态,创建老师,学生人数*/
    @JsonProperty("C_code")
    @ApiModelProperty(value = "C_code")
    private String C_code;
    @JsonProperty("C_name")
    @ApiModelProperty(value = "C_name")
    private String C_name;
    @JsonProperty("C_cname")
    @ApiModelProperty(value = "C_cname")
    private String C_cname;
    @JsonProperty("C_year")
    @ApiModelProperty(value = "C_year")
    private String C_year;
    @JsonProperty("C_term")
    @ApiModelProperty(value = "C_term")
    private String C_term;
    @JsonProperty("Codeuse")
    @ApiModelProperty(value = "Codeuse")
    private Byte Codeuse;
    @JsonProperty("C_info")
    @ApiModelProperty(value = "C_info")
    private String C_info;
    @JsonProperty("T_name")
    @ApiModelProperty(value = "T_name")
    private String T_name;
    @JsonProperty("C_menber")
    @ApiModelProperty(value = "C_menber")
    private Integer C_menber;

    public String getC_code() {
        return C_code;
    }

    public void setC_code(String c_code) {
        C_code = c_code;
    }

    public String getC_name() {
        return C_name;
    }

    public void setC_name(String c_name) {
        C_name = c_name;
    }

    public String getC_cname() {
        return C_cname;
    }

    public void setC_cname(String c_cname) {
        C_cname = c_cname;
    }

    public String getC_year() {
        return C_year;
    }

    public void setC_year(String c_year) {
        C_year = c_year;
    }

    public String getC_term() {
        return C_term;
    }

    public void setC_term(String c_term) {
        C_term = c_term;
    }

    public byte getCodeuse() {
        return Codeuse;
    }

    public void setCodeuse(byte codeuse) {
        Codeuse = codeuse;
    }

    public String getC_info() {
        return C_info;
    }

    public void setC_info(String c_info) {
        C_info = c_info;
    }

    public String getT_name() {
        return T_name;
    }

    public void setT_name(String t_name) {
        T_name = t_name;
    }

    public void setCodeuse(Byte codeuse) {
        Codeuse = codeuse;
    }

    public Integer getC_menber() {
        return C_menber;
    }

    public void setC_menber(Integer c_menber) {
        C_menber = c_menber;
    }
}