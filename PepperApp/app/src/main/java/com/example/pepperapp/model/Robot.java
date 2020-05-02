package com.example.pepperapp.model;

public class Robot {
    private String mRobotName;
    private String mRobotIPAddress;
    private String mRobotPort;

    public Robot(String mRobotName, String mRobotIPAddress, String port) {
        this.mRobotName = mRobotName;
        this.mRobotIPAddress = mRobotIPAddress;
        this.mRobotPort = port;
    }

    public String getmRobotName() {
        return mRobotName;
    }

    public void setmRobotName(String mRobotName) {
        this.mRobotName = mRobotName;
    }

    public String getmRobotIPAddress() {
        return mRobotIPAddress;
    }

    public void setmRobotIPAddress(String mRobotIPAddress) {
        this.mRobotIPAddress = mRobotIPAddress;
    }

    public String getmRobotPort() {
        return mRobotPort;
    }

    public void setmRobotPort(String port) {
        this.mRobotPort = port;
    }
}
