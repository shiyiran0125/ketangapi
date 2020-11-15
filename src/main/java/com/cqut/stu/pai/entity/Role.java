package com.cqut.stu.pai.entity;

/**
 * @author 石益然
 * @program: pai
 * @description: 这是封装角色的类
 * @date 2020-11-14 16:36:02
 */
public class Role {
    private Integer id;
    private String name;//角色英语名
    private String nameCh;//中文名字

    public Role(Integer id, String name, String nameCh) {
        this.id = id;
        this.name = name;
        this.nameCh = nameCh;
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

    public String getNameCh() {
        return nameCh;
    }

    public void setNameCh(String nameCh) {
        this.nameCh = nameCh;
    }
}