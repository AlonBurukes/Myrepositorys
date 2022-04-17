package com.entity;

public class Keeper {
    private  String keeperId;
    private  String keeperName;
    private  String keeperPassword;
    private  String keeperTel;
    private  String keeperSex;


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

    public String getKeeperPassword() {
        return keeperPassword;
    }

    public void setKeeperPassword(String keeperPassword) {
        this.keeperPassword = keeperPassword;
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
        return "Keeper{" +
                "keeperId='" + keeperId + '\'' +
                ", keeperName='" + keeperName + '\'' +
                ", keeperPassword='" + keeperPassword + '\'' +
                ", keeperTel='" + keeperTel + '\'' +
                ", keeperSex='" + keeperSex + '\'' +
                '}';
    }
}
