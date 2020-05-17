package com.example.pepperapp.Controller;

import android.util.Log;
import android.widget.Toast;

import com.example.pepperapp.model.Robot;
import com.example.pepperapp.model.TCPServerParam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
    private TCPServerParam mServer;
    private ClientThread mClientThread;
    private Socket mSocket;
    private Thread mThread;
    private boolean isConnectionSuccessful;
    private boolean isClosingSuccessful;

    public TCPClient(Robot robot) {
        mServer = new TCPServerParam(robot.getmRobotName(), robot.getmRobotIPAddress(), Integer.parseInt(robot.getmRobotPort()));
        mClientThread = new ClientThread();
        mThread = new Thread(mClientThread);
        isConnectionSuccessful = true;
        isClosingSuccessful = true;
    }

    class ClientThread implements Runnable {
        private final String SERVER_IP = mServer.getmServerIPAddress();
        private final int SERVER_PORT = mServer.getmServerPort();
        @Override
        public void run() {
            InetAddress address = null;
            try {
                address = InetAddress.getByName(SERVER_IP);
                mSocket = new Socket(address, SERVER_PORT);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                isConnectionSuccessful = false;
            }

            /*new Thread(new Runnable() {
                private InputStreamReader mInputStreamReader;
                private BufferedReader mBufferedReader;

                @Override
                public void run() {
                    try {
                        if(isConnectionSuccessful){
                            mInputStreamReader = new InputStreamReader(mSocket.getInputStream());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mBufferedReader = new BufferedReader(mInputStreamReader);
                    if (mSocket != null) {
                        try {
                            String message = mBufferedReader.readLine();
                            Log.d("Message", message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();*/


        }
    }

    public void disconnectSocket() {
        if (mSocket.isConnected() || !mSocket.isClosed()) {
            try {
                mSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
                isClosingSuccessful = false;
            }
        }
    }

    public void sendToServer() {
        new Thread(new Runnable() {
            private OutputStreamWriter mOutputStreamWriter;
            private BufferedWriter mBufferedWriter;

            @Override
            public void run() {
                try {
                    mOutputStreamWriter = new OutputStreamWriter(mSocket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                if (null != mSocket) {
                    PrintWriter out = new PrintWriter(mBufferedWriter, true);
                    out.flush();
                    int i =0;
                    while(true){
                        out.println(i++ +" hello !");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public Thread getmThread() {
        return mThread;
    }


    public boolean isConnectionSuccessful(){
        return isConnectionSuccessful;
    }

    public boolean isCLosingSuccessful(){
        return isClosingSuccessful;
    }


}
