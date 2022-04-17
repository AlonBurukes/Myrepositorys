package com.entity;

public class Station {
    private String staId;
    private String staLoc;
    private String keeperId;

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
        return "Station{" +
                "staId='" + staId + '\'' +
                ", staLoc='" + staLoc + '\'' +
                ", keeperId='" + keeperId + '\'' +
                '}';
    }
}
