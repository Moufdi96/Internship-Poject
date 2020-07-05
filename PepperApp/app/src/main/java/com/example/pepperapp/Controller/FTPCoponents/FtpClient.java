package com.example.pepperapp.Controller.FTPCoponents;

import com.example.pepperapp.model.Robot;
import com.example.pepperapp.model.TCPServerParam;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPCmd;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class FtpClient {
    private TCPServerParam mServer;
    private FtpClient.ClientThread mClientThread;
    private Thread mThread;
    private FTPClient mFTPClient;
    private boolean isConnectionSuccessful;
    private boolean isLoginSuccessful;
    private boolean isClosingSuccessful;

    public FtpClient(Robot robot) {
        this.mServer = new TCPServerParam(robot.getmRobotName(), robot.getmRobotIPAddress(), Integer.parseInt(robot.getmRobotPort()));
        this.mClientThread = new FtpClient.ClientThread();
        this.mFTPClient = new FTPClient();
        this.mThread = new Thread(mClientThread);
        this.isConnectionSuccessful = false;
        this.isLoginSuccessful = false;
        this.isClosingSuccessful = false;
    }

    class ClientThread implements Runnable {
        private String mConnectionFeedback = "";
        private String mLoginFeedback = "";

        @Override
        public void run() {
            this.mConnectionFeedback = connect(mServer);
            this.mLoginFeedback = login("moufdi", "taha");
        }
    }

    public void disconnect() {
        if (isConnected() || isLoging()) {

            try {
                //this.mFTPClient.logout();
                this.mFTPClient.disconnect();
                this.isConnectionSuccessful = false;
                this.isClosingSuccessful = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Thread getmThread() {
        return mThread;
    }

    public FTPClient getFTPClient() {
        return mFTPClient;
    }

    public boolean isConnectionSuccessful() {
        return isConnectionSuccessful;
    }

    public boolean isCLosingSuccessful() {
        return isClosingSuccessful;
    }

    public String connect(TCPServerParam mServer) {
        InetAddress address = null;

        try {
            address = InetAddress.getByName(mServer.getmServerIPAddress());
            mFTPClient.connect(address, mServer.getmServerPort());
            isConnectionSuccessful = true;
            isClosingSuccessful = false;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
            isConnectionSuccessful = false;
            isClosingSuccessful = true;
        } catch (IOException e) {
            e.printStackTrace();
            isConnectionSuccessful = false;
            isClosingSuccessful = true;
        }
        this.mFTPClient.enterLocalPassiveMode();
        return mFTPClient.getReplyString();
    }

    public String login(String user, String pass) {
        if (this.isConnected()) {
            try {
                this.mFTPClient.sendCommand(FTPCmd.USER, user);
                this.mFTPClient.sendCommand(FTPCmd.PASS, pass);
                this.isLoginSuccessful = (this.mFTPClient.getReplyCode() == 230);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mFTPClient.getReplyString();
    }

    public boolean isConnected() {
        return this.isConnectionSuccessful;
    }

    public boolean isLoging() {
        return this.isLoginSuccessful;
    }

}
