package com.entity;

public class Customer{
    //id
    private String cusId;
    //姓名&&用户名
    private String cusName;
    //密码
    private String cusPassWord;
    //客户地址
    private String cusLoc;
    //联系方式
    private String cusTel;
    //注册时长
    private int cunDate;


    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusPassWord() {
        return cusPassWord;
    }

    public void setCusPassWord(String cusPassWord) {
        this.cusPassWord = cusPassWord;
    }

    public String getCusLoc() {
        return cusLoc;
    }

    public void setCusLoc(String cusLoc) {
        this.cusLoc = cusLoc;
    }

    public String getCusTel() {
        return cusTel;
    }

    public void setCusTel(String cusTel) {
        this.cusTel = cusTel;
    }

    public int getCunDate() {
        return cunDate;
    }

    public void setCunDate(int cunDate) {
        this.cunDate = cunDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cusId='" + cusId + '\'' +
                ", cusName='" + cusName + '\'' +
                ", cusPassWord='" + cusPassWord + '\'' +
                ", cusLoc='" + cusLoc + '\'' +
                ", cusTel='" + cusTel + '\'' +
                ", cunDate='" + cunDate + '\'' +
                '}';
    }
}