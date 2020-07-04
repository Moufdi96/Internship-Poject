/*package com.example.ftpclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPCmd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private final int STORAGE_PERMISSION_CODE = 1;
    private FTPClient ftpClient;
    private final String SERVER_IP = "134.245.109.74";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ftpClient = new FTPClient();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InetAddress add = InetAddress.getByName(SERVER_IP);
                    ftpClient.connect(add, 1040);
                    ftpClient.sendCommand(FTPCmd.USER, "moufdi");
                    Log.d("USER", ftpClient.getReplyString());
                    ftpClient.sendCommand(FTPCmd.PASS, "taha");
                    Log.d("PASS", ftpClient.getReplyString());
                    ftpClient.enterLocalPassiveMode();
                    //ftpClient.login("moufdi","taha");
                    ftpClient.changeWorkingDirectory("Documents/FTPUploadedFiles");
                    //ftpClient.sendCommand("PLAY");
                    //Log.d("PLAY",ftpClient.getReplyString());
                    //boolean status = ftpClient.appendFile("uploadme.txt",i);
                    //i.close();

                    //ftpClient.deleteFile("uploadme.txt");
                    //FileInputStream srcFileStream = openFileInput("/data/data/com.example.ftpclient/fichier1.txt");
                    String permission = Manifest.permission.READ_EXTERNAL_STORAGE;
                    checkPermission(permission);
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        boolean status = false;
                        //File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "ALgeria.png");
                        //FileOutputStream fos = new FileOutputStream(f);
                        //int a = 1;
                        //status = ftpClient.retrieveFile("3.png", fos);
                        //fos.close();
                        //Log.d("downlad",""+ status);
                        FileInputStream i = new FileInputStream("/data/data/com.example.ftpclient/NewTextFile.txt");
                        //FileInputStream i = new FileInputStream(f);
                        status = ftpClient.storeFile("t.txt",i);
                        i.close();
                    }

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    public void checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        if (check == PackageManager.PERMISSION_GRANTED) {
        } else {
            requestStoragePermission();
        }
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
}

 */

package com.example.ftpclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPCmd;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private final int STORAGE_PERMISSION_CODE = 1;
    private FTPClient ftpClient;
    private final String SERVER_IP = "134.245.109.74";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ftpClient = new FTPClient();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InetAddress add = InetAddress.getByName(SERVER_IP);
                    ftpClient.connect(add, 1040);
                    ftpClient.sendCommand(FTPCmd.USER, "moufdi");
                    Log.d("USER", ftpClient.getReplyString());
                    ftpClient.sendCommand(FTPCmd.PASS, "taha");
                    Log.d("PASS", ftpClient.getReplyString());
                    ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
                    ftpClient.enterLocalPassiveMode();
                    //ftpClient.login("moufdi","taha");
                    ftpClient.changeWorkingDirectory("Documents/FTPUploadedFiles");
                    ftpClient.sendCommand("PLAY");
                    Log.d("PLAY", ftpClient.getReplyString());

                    //boolean status = ftpClient.appendFile("uploadme.txt",i);
                    //i.close();

                    //ftpClient.deleteFile("uploadme.txt");
                    //FileInputStream srcFileStream = openFileInput("/data/data/com.example.ftpclient/fichier1.txt");
                        boolean status = false;
                        File f = new File("/data/data/com.example.ftpclient/hello.txt");
                        FileOutputStream fos = new FileOutputStream(f);
                        //int a = 1;
                        //status = ftpClient.retrieveFile("t.txt", fos);
                        fos.close();
                        //Log.d("downlad",""+ status);
                        //FileInputStream i = new FileInputStream(f);
                        //status = ftpClient.storeFile("t.txt",i);
                        //i.close();


                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}