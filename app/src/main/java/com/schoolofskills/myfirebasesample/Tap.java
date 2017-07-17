package com.schoolofskills.myfirebasesample;

/**
 * Created by premkumar on 30/05/2016.
 */
public class Tap {
    private String mTapValue, mTapRemark;

    public Tap() {
    }

    public Tap(String tapValue, String tapRemark) {
        mTapValue = tapValue;
        mTapRemark = tapRemark;
    }

    public String getTapValue() {
        return mTapValue;
    }

    public String getTapRemark() {
        return mTapRemark;
    }
}
