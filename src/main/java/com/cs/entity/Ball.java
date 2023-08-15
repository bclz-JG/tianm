package com.cs.entity;

import lombok.Data;
import lombok.Generated;

public class Ball {

    private int csNum;

    private float csPoss;

    private Integer csCount;

    private float csRatio;

    public int getCsNum() {
        return csNum;
    }

    public void setCsNum(int csNum) {
        this.csNum = csNum;
    }

    public float getCsPoss() {
        return csPoss;
    }

    public void setCsPoss(float csPoss) {
        this.csPoss = csPoss;
    }

    public Integer getCsCount() {
        return csCount;
    }

    public void setCsCount(Integer csCount) {
        this.csCount = csCount;
    }

    public float getCsRatio() {
        return csRatio;
    }

    public void setCsRatio(float csRatio) {
        this.csRatio = csRatio;
    }
}
