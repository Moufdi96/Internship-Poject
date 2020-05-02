package com.example.pepperapp.model;

import android.content.Context;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonParseRobotList extends JsonFile implements JsonFile.JsonParsing {

    private static final String JSON__LIST_FILE = "robot_ListFile.json";
    private List<Robot> mRobotList;
    //private String mJsonRobotList;


    public JsonParseRobotList(Context currentContext) {
        super(currentContext,JSON__LIST_FILE);
        this.mRobotList = new ArrayList<>();
    }

    @Override
    public String javaObjectToJson() {
        String json = mGson.toJson(mRobotList);
        return json;
    }

    @Override
    public void jsonToJavaObject() {
        Type RobotListType = new TypeToken<ArrayList<Robot>>() {}.getType();
        mRobotList = mGson.fromJson(this.loadedJson, RobotListType);
    }

    public List<Robot> getmRobotList() {
        return mRobotList;
    }
}

















