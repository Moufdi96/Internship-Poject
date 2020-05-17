package com.example.pepperapp.model;

import android.content.Context;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonParseMovementList extends JsonFile implements JsonFile.JsonParsing {
    private static final String JSON__LIST_FILE = "movementListFile.json";
    private List<Robot> mMovementList;

    public JsonParseMovementList(Context currentContext, String jsonFile) {
        super(currentContext, jsonFile);
    }

    @Override
    public String javaObjectToJson() {
        String json = mGson.toJson(this.mMovementList);
        return json;
    }

    @Override
    public void jsonToJavaObject() {
        Type MovementListType = new TypeToken<ArrayList<Movement>>(){}.getType();
        this.mMovementList = mGson.fromJson(loadedJson, MovementListType);
    }

    public List<Robot> getmMovementList() {
        return mMovementList;
    }
}
