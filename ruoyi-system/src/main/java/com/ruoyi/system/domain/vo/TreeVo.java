package com.ruoyi.system.domain.vo;


import java.util.List;

public class TreeVo {
    private String name;
    private List children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getChildren() {
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }
}
