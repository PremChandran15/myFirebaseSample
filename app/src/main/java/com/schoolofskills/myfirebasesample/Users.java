package com.schoolofskills.myfirebasesample;

/**
 * Created by premkumar on 19/04/2016.
 */

//My plain old Java Object for Users class
public class Users {
    private String mName;
    private String mEmail;
    private int mEmployeeID;
    private String mJob;

    public Users() {
    }

    public Users(String name, String email, int employeeID, String job) {
        mName = name;
        mEmail = email;
        mEmployeeID = employeeID;
        mJob = job;
    }


    public String getName() {
        return mName;
    }


    public void setName(String name) {

        mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public int getEmployeeID() {
        return mEmployeeID;
    }

    public void setEmployeeID(int employeeID) {
        mEmployeeID = employeeID;
    }

    public String getJob() {
        return mJob;
    }

    public void setJob(String job) {
        mJob = job;
    }
}
