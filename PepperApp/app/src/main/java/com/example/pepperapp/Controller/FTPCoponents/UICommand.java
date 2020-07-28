package com.example.pepperapp.Controller.FTPCoponents;

import android.content.Context;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class UICommand {
    private FTPClient mFTPClient;

    public enum UIRequest {
        PLAY_MOVEMENT, DELETE_MOVEMENT,QUIT,ACTIVATE_ANIMATION_MODE,DEACTIVATE_ANIMATION_MODE,SAVE_MOVEMENT,GENERATE_ID
    }

    public UICommand(FTPClient ftpClient) {
        this.mFTPClient = ftpClient;
    }

    public void sendCommandToServer(final UIRequest command, final Context c) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mFTPClient.sendCommand(command.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    //Toast.makeText(c,"No connection to robot detected !",Toast.LENGTH_LONG).show();
                }
            }
        }).start();
    }

    public void sendCommandToServer(final UIRequest command, final String mvtID, final Context c) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mFTPClient.sendCommand(command.toString(), mvtID);
                } catch (IOException e) {
                    e.printStackTrace();
                    //Toast.makeText(c,"No connection to robot detected !",Toast.LENGTH_LONG).show();
                }
            }
        }).start();
    }

    /*public int feedbackFromServer() {
        int code = 0;
        code = mFTPClient.getReplyCode();
        return code;
    }*/

    public String feedbackFromServer() {
        String s = this.mFTPClient.getReplyString().trim();
        return s;
    }

    public FTPClient getFTPClient() {
        return mFTPClient;
    }
}
