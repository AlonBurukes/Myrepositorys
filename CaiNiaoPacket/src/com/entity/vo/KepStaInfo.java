package com.entity.vo;

public class KepStaInfo {
    private String keeperId;
    private String staId;
    private String staLoc;

    public String getKeeperId() {
        return keeperId;
    }

    public void setKeeperId(String keeperId) {
        this.keeperId = keeperId;
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

    @Override
    public String toString() {
        return "KepStaInfo{" +
                "keeperId='" + keeperId + '\'' +
                ", staId='" + staId + '\'' +
                ", staLoc='" + staLoc + '\'' +
                '}';
    }
}
