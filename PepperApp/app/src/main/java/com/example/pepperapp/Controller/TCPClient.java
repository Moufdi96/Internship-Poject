package com.example.pepperapp.Controller;

import com.example.pepperapp.model.Robot;
import com.example.pepperapp.model.TCPServerParam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {
    private TCPServerParam mServer;
    private ClientThread clientThread;
    private Thread thread;

    public TCPClient(Robot robot){
        mServer = new TCPServerParam(robot.getmRobotName(),robot.getmRobotIPAddress(),Integer.getInteger(robot.getmRobotPort()));
    }

    class ClientThread implements Runnable {
        private final String SERVER_IP = mServer.getmServerIPAddress();
        private final int SERVER_PORT = mServer.getmServerPort();
        private Socket mSocket;

        @Override
        public void run() {
            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                this.mSocket = new Socket(serverAddr, SERVER_PORT);
                OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(this.mSocket.getOutputStream());
                BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                if (null != this.mSocket) {
                    PrintWriter out = new PrintWriter(mBufferedWriter,true);
                    out.flush();
                    out.println("Just for test");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


}
