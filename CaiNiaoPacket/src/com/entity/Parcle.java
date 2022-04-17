package com.entity;

public class Parcle {
    //包裹id
    private String parId;
    //包裹数量
    private int parNum;
    //总费用
    private double parSum;
    //收费类型的id
    private int chaId;
    //驿站id
    private String staId;
    //物流公司id
    private int  comId;
    //包裹始发地
    private String parStart;
    //客户Id
    private String cusId;

    public String getParId() {
        return parId;
    }

    public void setParId(String parId) {
        this.parId = parId;
    }

    public int getParNum() {
        return parNum;
    }

    public void setParNum(int parNum) {
        this.parNum = parNum;
    }

    public double getParSum() {
        return parSum;
    }

    public void setParSum(double parSum) {
        this.parSum = parSum;
    }

    public int getChaId() {
        return chaId;
    }

    public void setChaId(int chaId) {
        this.chaId = chaId;
    }

    public String getStaId() {
        return staId;
    }

    public void setStaId(String staId) {
        this.staId = staId;
    }

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    public String getParStart() {
        return parStart;
    }

    public void setParStart(String parStart) {
        this.parStart = parStart;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    @Override
    public String toString() {
        return "Parcle{" +
                "parId='" + parId + '\'' +
                ", parNum=" + parNum +
                ", parSum=" + parSum +
                ", chaId=" + chaId +
                ", staId='" + staId + '\'' +
                ", comId=" + comId +
                ", parStart='" + parStart + '\'' +
                ", cusId='" + cusId + '\'' +
                '}';
    }
}
