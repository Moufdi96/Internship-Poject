package com.example.pepperapp.Controller.FTPCoponents;

import org.apache.commons.net.ftp.FTPClient;

public class FTPTransferData  {

    private FTPClient mFTPClient;

    public FTPTransferData(FTPClient ftpClient) {
        this.mFTPClient = ftpClient;
    }

}
