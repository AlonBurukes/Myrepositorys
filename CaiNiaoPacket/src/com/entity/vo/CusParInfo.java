package com.entity.vo;

/**
 * 顾客包裹信息
 */
public class CusParInfo {
    //客户id
    private String cusId;
   //客户姓名
    private String cusName;
    //包裹数量
    private int parNum;
    //包裹总额
    private String parSum;
    //包裹始发地
    private String parStart;
    //驿站地点 即 包裹所在地
    private String staLoc;
    //收费类型
    private String chaStyle;
    //物流公司
    private String comName;
    //驿站管理员姓名
    private String keeperName;
    //驿站管理员电话
    private String keeperTel;

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

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

    public int getParNum() {
        return parNum;
    }

    public void setParNum(int parNum) {
        this.parNum = parNum;
    }

    public String getParSum() {
        return parSum;
    }

    public void setParSum(String parSum) {
        this.parSum = parSum;
    }

    public String getParStart() {
        return parStart;
    }

    public void setParStart(String parStart) {
        this.parStart = parStart;
    }

    public String getStaLoc() {
        return staLoc;
    }

    public void setStaLoc(String staLoc) {
        this.staLoc = staLoc;
    }

    public String getChaStyle() {
        return chaStyle;
    }

    public void setChaStyle(String chaStyle) {
        this.chaStyle = chaStyle;
    }

    public String getKeeperName() {
        return keeperName;
    }

    public void setKeeperName(String keeperName) {
        this.keeperName = keeperName;
    }

    public String getKeeperTel() {
        return keeperTel;
    }

    public void setKeeperTel(String keeperTel) {
        this.keeperTel = keeperTel;
    }

    @Override
    public String toString() {
        return "CusParInfo{" +
                "cusId='" + cusId + '\'' +
                ", cusName='" + cusName + '\'' +
                ", parNum=" + parNum +
                ", parSum='" + parSum + '\'' +
                ", parStart='" + parStart + '\'' +
                ", staLoc='" + staLoc + '\'' +
                ", chaStyle='" + chaStyle + '\'' +
                ", comName='" + comName + '\'' +
                ", keeperName='" + keeperName + '\'' +
                ", keeperTel='" + keeperTel + '\'' +
                '}';
    }
}
