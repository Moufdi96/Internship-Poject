package com.example.pepperapp.model;

public class TCPServerParam {

    private String mServerName;
    private String mServerIPAddress;
    private int mServerPort;

    public TCPServerParam(String mServerName, String mServerIPAddress, int mServerPort) {
        this.mServerName = mServerName;
        this.mServerIPAddress = mServerIPAddress;
        this.mServerPort = mServerPort;
    }

    public String getmServerName() {
        return mServerName;
    }

    public String getmServerIPAddress() {
        return mServerIPAddress;
    }

    public int getmServerPort() {
        return mServerPort;
    }
}
