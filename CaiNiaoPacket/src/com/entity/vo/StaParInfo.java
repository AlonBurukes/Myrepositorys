package com.entity.vo;

/**
 * 驿站包裹信息
 */
public class StaParInfo {
    //包裹Id
    private String parId;
    //包裹数量
    private int parNum;
    //包裹始发地
    private String parStart;
    //物流公司名称
    private String comName;
    //包裹的客户Id
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

    public String getParStart() {
        return parStart;
    }

    public void setParStart(String parStart) {
        this.parStart = parStart;
    }

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

    @Override
    public String toString() {
        return "StaParInfo{" +
                "parId='" + parId + '\'' +
                ", parNum=" + parNum +
                ", parStart='" + parStart + '\'' +
                ", comName='" + comName + '\'' +
                ", cusId='" + cusId + '\'' +
                '}';
    }
}
