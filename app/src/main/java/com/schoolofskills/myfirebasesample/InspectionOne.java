package com.schoolofskills.myfirebasesample;

import java.util.HashMap;

/**This is POJO for Inpection One. Use this for just getting the data and showing it in the database
 * Created by premkumar on 05/05/2016.
 */
public class InspectionOne {

    private double mCapacitanceValue, mCurrentValue;
    private HashMap<String, Object> mCreatedAt;
    private Flowmeter mFlowmeter;
    private Tap mTap;
    private Pipe mPipe;
    private ValueAndRemark mResistance, mVoltage;
    private HashMap<String,Object> mDefectsDetected;

    public InspectionOne() {
    }

    public InspectionOne(double capacitanceValue, double currentValue, HashMap<String, Object> createdAt, Flowmeter flowmeter, Tap tap, Pipe pipe, ValueAndRemark resistance, ValueAndRemark voltage, HashMap<String,Object> defectsDetected) {
        mCapacitanceValue = capacitanceValue;
        mCurrentValue = currentValue;
        mCreatedAt = createdAt;
        mFlowmeter = flowmeter;
        mTap = tap;
        mPipe = pipe;
        mResistance = resistance;
        mVoltage = voltage;
        mDefectsDetected = defectsDetected;
    }

    public InspectionOne(double capacitanceValue, double currentValue, HashMap<String, Object> createdAt, Tap tap, Pipe pipe, ValueAndRemark resistance, HashMap<String,Object> defectsDetected) {
        mCapacitanceValue = capacitanceValue;
        mCurrentValue = currentValue;
        mCreatedAt = createdAt;
        mTap = tap;
        mPipe = pipe;
        mResistance = resistance;
        mDefectsDetected = defectsDetected;
    }

    public double getCapacitanceValue() {
        return mCapacitanceValue;
    }

    public double getCurrentValue() {
        return mCurrentValue;
    }

    public HashMap<String, Object> getCreatedAt() {
        return mCreatedAt;
    }

    public ValueAndRemark getResistance() {
        return mResistance;
    }

    public ValueAndRemark getVoltage() {
        return mVoltage;
    }

    public Flowmeter getFlowmeter() {
        return mFlowmeter;
    }

    public Tap getTap() {
        return mTap;
    }

    public Pipe getPipe() {
        return mPipe;
    }

    public HashMap<String, Object> getDefectsDetected() {
        return mDefectsDetected;
    }
}

