package com.example.pepperapp.Controller.FTPCoponents;

import android.content.Context;
import android.widget.Toast;

import com.example.pepperapp.Controller.CreatNewMovementFragment;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class UICommand {
    private FTPClient mFTPClient;

    public enum UIRequest {
        CREATE_NEW_MOVEMENT, PLAY_MOVEMENT, CREATE_NEW_ACTIVITY, PLAY_ACTIVITY,PLAY
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

    public String feedbackFromServer() {
        return mFTPClient.getReplyString();
    }

    public FTPClient getFTPClient() {
        return mFTPClient;
    }
}
