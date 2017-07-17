package com.schoolofskills.myfirebasesample;

/**
 * Created by premkumar on 08/07/2016.
 */
public class DefectsDetected {
    private String mDefects, mSeverity, mAction;

    public DefectsDetected() {
    }

    public DefectsDetected(String defects, String severity, String action) {
        mDefects = defects;
        mSeverity = severity;
        mAction = action;
    }

    public String getDefects() {
        return mDefects;
    }

    public String getSeverity() {
        return mSeverity;
    }

    public String getAction() {
        return mAction;
    }
}
