package com.schoolofskills.myfirebasesample;

import com.google.firebase.database.Exclude;

/**
 * Created by premkumar on 23/04/2016.
 */

public class Location1 {
    private String location;
    private int stationNumber;
    @Exclude
    private String mUser;

    public Location1() {
    }

    public Location1(String location, int stationNumber) {
        this.location = location;
        this.stationNumber = stationNumber;
    }

    public String getLocation() {
        return location;
    }

    public long getStationNumber() {
        return stationNumber;
    }

}
