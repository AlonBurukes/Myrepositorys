package com.entity;

public class Charges {
    private int chaId;
    private String chaStyle;
    private int chaFees;

    public int getChaId() {
        return chaId;
    }

    public void setChaId(int chaId) {
        this.chaId = chaId;
    }

    public String getChaStyle() {
        return chaStyle;
    }

    public void setChaStyle(String chaStyle) {
        this.chaStyle = chaStyle;
    }

    public int getChaFees() {
        return chaFees;
    }

    public void setChaFees(int chaFee) {
        this.chaFees = chaFee;
    }

    @Override
    public String toString() {
        return "Charges{" +
                "chaId=" + chaId +
                ", chaStyle='" + chaStyle + '\'' +
                ", chaFees=" + chaFees +
                '}';
    }
}
