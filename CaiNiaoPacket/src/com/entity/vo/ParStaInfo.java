package com.entity.vo;
/**
 * 包裹所在的驿站信息
 */

public class ParStaInfo {
    //包裹id
    private String parId;
    //驿站id
    private String staId;
    //驿站地点
    private String staLoc;
    //驿站管理者id
    private String keeperId;

    public String getParId() {
        return parId;
    }

    public void setParId(String parId) {
        this.parId = parId;
    }

    public String getStaId() {
        return staId;
    }

    public void setStaId(String staId) {
        this.staId = staId;
    }

    public String getStaLoc() {
        return staLoc;
    }

    public void setStaLoc(String staLoc) {
        this.staLoc = staLoc;
    }

    public String getKeeperId() {
        return keeperId;
    }

    public void setKeeperId(String keeperId) {
        this.keeperId = keeperId;
    }

    @Override
    public String toString() {
        return "ParStaInfo{" +
                "parId='" + parId + '\'' +
                ", staId='" + staId + '\'' +
                ", staLoc='" + staLoc + '\'' +
                ", keeperId='" + keeperId + '\'' +
                '}';
    }
}