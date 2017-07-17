package com.schoolofskills.myfirebasesample;

/**
 * Created by premkumar on 30/05/2016.
 */
public class Voltage {

    private double mVoltageValue;
    private String mVoltageRemark;

    public Voltage() {
    }

    public Voltage(double voltageValue, String voltageRemark) {
        mVoltageValue = voltageValue;
        mVoltageRemark = voltageRemark;
    }

    public double getVoltageValue() {
        return mVoltageValue;
    }

    public String getVoltageRemark() {
        return mVoltageRemark;
    }
}
