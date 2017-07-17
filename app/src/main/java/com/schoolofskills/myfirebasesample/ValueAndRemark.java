package com.schoolofskills.myfirebasesample;

/**
 * Created by premkumar on 21/06/2016.
 */
public class ValueAndRemark {
    private double mValue;
    private String mRemark;

    public ValueAndRemark() {
    }

    public ValueAndRemark(double value, String remark) {
        mValue = value;
        mRemark = remark;
    }

    public double getValue() {
        return mValue;
    }

    public String getRemark() {
        return mRemark;
    }
}
