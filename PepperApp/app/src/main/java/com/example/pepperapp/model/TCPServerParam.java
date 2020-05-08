package com.example.pepperapp.model;

public class TCPServerParam {

    private String mServerName;
    private String mServerIPAddress;
    private String mServerPort;

    public TCPServerParam(String mServerName, String mServerIPAddress, String mServerPort) {
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

    public String getmServerPort() {
        return mServerPort;
    }
}
