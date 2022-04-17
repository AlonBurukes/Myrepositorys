package com.entity.vo;

public class StaKepInfo {
    private String staId;
    private String keeperId;
    private String keeperName;
    private String keeperTel;
    private String keeperSex;

    public String getStaId() {
        return staId;
    }

    public void setStaId(String staId) {
        this.staId = staId;
    }

    public String getKeeperId() {
        return keeperId;
    }

    public void setKeeperId(String keeperId) {
        this.keeperId = keeperId;
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

    public String getKeeperSex() {
        return keeperSex;
    }

    public void setKeeperSex(String keeperSex) {
        this.keeperSex = keeperSex;
    }

    @Override
    public String toString() {
        return "StaKepInfo{" +
                "staId='" + staId + '\'' +
                ", keeperId='" + keeperId + '\'' +
                ", keeperName='" + keeperName + '\'' +
                ", keeperTel='" + keeperTel + '\'' +
                ", keeperSex='" + keeperSex + '\'' +
                '}';
    }
}
