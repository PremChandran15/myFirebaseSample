package com.schoolofskills.myfirebasesample;

/**
 * Created by premkumar on 27/05/2016.
 */
public class Resistance {
    private double mResistance;
    private String mResistanceRemark;

    public Resistance() {
    }

    public Resistance(double resistance, String resistanceRemark) {
        mResistance = resistance;
        mResistanceRemark = resistanceRemark;
    }

    public double getResistance() {
        return mResistance;
    }

    public String getResistanceRemark() {
        return mResistanceRemark;
    }
}
