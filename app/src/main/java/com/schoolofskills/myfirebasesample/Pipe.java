package com.schoolofskills.myfirebasesample;

/**
 * Created by premkumar on 30/05/2016.
 */
public class Pipe {

    private String mPipeValue, mPipeRemark;

    public Pipe() {
    }

    public Pipe(String pipeValue, String pipeRemark) {
        mPipeValue = pipeValue;
        mPipeRemark = pipeRemark;
    }

    public String getPipeValue() {
        return mPipeValue;
    }

    public String getPipeRemark() {
        return mPipeRemark;
    }
}
