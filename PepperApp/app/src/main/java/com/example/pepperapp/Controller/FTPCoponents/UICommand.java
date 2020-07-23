package com.example.pepperapp.Controller.FTPCoponents;

import android.content.Context;
import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;

public class UICommand {
    private FTPClient mFTPClient;

    public enum UIRequest {
        PLAY_MOVEMENT, CREATE_NEW_ACTIVITY, PLAY_ACTIVITY,PLAY,QUIT,ACTIVATE_ANIMATION_MODE,DEACTIVATE_ANIMATION_MODE
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

    /*public int feedbackFromServer() {
        int code = 0;
        code = mFTPClient.getReplyCode();
        return code;
    }*/

    public String feedbackFromServer() {
       return this.mFTPClient.getReplyString().trim();
    }

    public FTPClient getFTPClient() {
        return mFTPClient;
    }
}
