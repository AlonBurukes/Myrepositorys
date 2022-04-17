package com.entity;

public class Company {
    private int comId;
    private String comName;

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    @Override
    public String toString() {
        return "Company{" +
                "comId=" + comId +
                ", comName='" + comName + '\'' +
                '}';
    }
}
