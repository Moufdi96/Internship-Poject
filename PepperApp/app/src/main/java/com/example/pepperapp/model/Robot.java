package com.example.pepperapp.model;

public class Robot {
    private String mRobotName;
    private String mRobotIPAddress;
    private String mRobotPort;
    private Boolean mConnectionStatus;

    public Robot(String mRobotName, String mRobotIPAddress, String port,Boolean mConnectionStatus) {
        this.mRobotName = mRobotName;
        this.mRobotIPAddress = mRobotIPAddress;
        this.mRobotPort = port;
        this.mConnectionStatus = mConnectionStatus;
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

    public Boolean getmConnectionStatus() {
        return mConnectionStatus;
    }

    public void setmConnectionStatus(Boolean mConnectionStatus) {
        this.mConnectionStatus = mConnectionStatus;
    }
}
