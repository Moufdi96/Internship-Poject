package com.example.pepperapp.Controller.FTPCoponents;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FTPTransferData {

    private int mFileTransferMode;
    private FTPClient mFTPClient;
    private File mFile;

    public FTPTransferData(FTPClient ftpClient) {
        this.mFTPClient = ftpClient;
        this.mFileTransferMode = FTP.BINARY_FILE_TYPE;
    }

    public void retrieveFile(final String fileName, final String serverWorkingDirectory, final String clientStorageDirectory) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FileOutputStream fos;
                mFile = new File(clientStorageDirectory.concat("/").concat(fileName));

                try {
                    fos = new FileOutputStream(mFile);
                    mFTPClient.changeWorkingDirectory(serverWorkingDirectory);
                    mFTPClient.retrieveFile(fileName, fos);
                    fos.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void sendFile(final String fileName, final String serverWorkingDirectory, final String clientStorageDirectory) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FileInputStream fis;
                mFile = new File(clientStorageDirectory.concat("/").concat(fileName));

                try {
                    fis = new FileInputStream(mFile);
                    mFTPClient.changeWorkingDirectory(serverWorkingDirectory);
                    mFTPClient.storeFile(fileName, fis);
                    fis.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public int getFileTransferMode() {
        return mFileTransferMode;
    }

    public void setFileTransferMode(int mFileTransferMode) {
        this.mFileTransferMode = mFileTransferMode;
    }

    public String feedbackFromServer() {
        return mFTPClient.getReplyString();
    }
}
