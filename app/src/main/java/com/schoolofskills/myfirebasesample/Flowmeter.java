package com.schoolofskills.myfirebasesample;

/**
 * Created by premkumar on 30/05/2016.
 */
public class Flowmeter {
    private String mFlowmeterValue, mFlowmeterRemark;

    public Flowmeter() {
    }

    public Flowmeter(String flowmeterValue, String flowmeterRemark) {
        mFlowmeterValue = flowmeterValue;
        mFlowmeterRemark = flowmeterRemark;
    }

    public String getFlowmeterValue() {
        return mFlowmeterValue;
    }

    public String getFlowmeterRemark() {
        return mFlowmeterRemark;
    }

}
