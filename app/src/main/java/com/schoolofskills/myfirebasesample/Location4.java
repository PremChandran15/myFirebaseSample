package com.schoolofskills.myfirebasesample;

import com.google.firebase.database.Exclude;

/**
 * Created by premkumar on 05/06/2016.
 */
public class Location4 {

    private String mLocation;
    private int mStationNumber;
    @Exclude
    private String mUser;

    public Location4() {
    }

    public Location4(String location, int stationNumber) {
        mLocation = location;
        mStationNumber = stationNumber;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getStationNumber() {
        return mStationNumber;
    }

}

