package com.example.pepperapp.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonFile {
    protected String mJsonFile;
    protected Context mCurrentContext;
    protected String loadedJson;


    public JsonFile(Context currentContext, String jsonFile) {
        this.mCurrentContext = currentContext;
        this.mJsonFile = jsonFile;
    }

    public void writeToJsonFile(String newParsedData) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = this.mCurrentContext.openFileOutput(mJsonFile, Context.MODE_PRIVATE);
            fileOutputStream.write(newParsedData.getBytes());
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean readJsonFile() {
        FileInputStream fileInputStream;
        InputStreamReader inputReader;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder = new StringBuilder();
        String loadedLine = "";
        try {
            fileInputStream = this.mCurrentContext.openFileInput(mJsonFile);
            inputReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputReader);
            while ((loadedLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(loadedLine);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.loadedJson = String.valueOf(stringBuilder);
        return true;
    }

    public static interface JsonParsing {
        Gson mGson = new Gson();

        public String javaObjectToJson();

        public void jsonToJavaObject();
    }
}
