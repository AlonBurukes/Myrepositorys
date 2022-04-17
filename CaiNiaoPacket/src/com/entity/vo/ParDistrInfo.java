package com.entity.vo;

/**
 * 包裹的物流信息
 */
public class ParDistrInfo {
    //包裹id
    private String parId;

    //始发站
    private String parStart;
    //包裹所在的驿站：即包裹现在所在地
    private String staLoc;
    //物流公司的名称
    private String comName;
    //包裹的类型
    private String chaStyle;

    public String getParId() {
        return parId;
    }

    public void setParId(String parId) {
        this.parId = parId;
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

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getChaStyle() {
        return chaStyle;
    }

    public void setChaStyle(String chaStyle) {
        this.chaStyle = chaStyle;
    }

    @Override
    public String toString() {
        return "ParDistrInfo{" +
                "parId='" + parId + '\'' +
                ", parStart='" + parStart + '\'' +
                ", staLoc='" + staLoc + '\'' +
                ", comName='" + comName + '\'' +
                ", chaStyle='" + chaStyle + '\'' +
                '}';
    }
}
