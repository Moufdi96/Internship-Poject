package com.example.pepperapp.Controller;

import android.content.Context;

import com.example.pepperapp.model.JsonFile;
import com.example.pepperapp.model.Movement;
import com.example.pepperapp.model.MovementType;
import com.example.pepperapp.model.Robot;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParseMovementLIst extends JsonFile implements JsonFile.JsonParsing {

    private static final String JSON__LIST_FILE = "movement_ListFile.json";
    private Map<MovementType,List<Movement>> mMovementList;
    public JsonParseMovementLIst(Context currentContext, Robot robot) {
        super(currentContext, robot.getmRobotName().concat("_").concat(JSON__LIST_FILE));
        this.mMovementList = new HashMap<>();
        this.mMovementList.put(MovementType.NECK,new ArrayList<Movement>());
        this.mMovementList.put(MovementType.SHOULDER,new ArrayList<Movement>());
        this.mMovementList.put(MovementType.ELBOW,new ArrayList<Movement>());
        this.mMovementList.put(MovementType.WRIST,new ArrayList<Movement>());
        this.mMovementList.put(MovementType.FIST,new ArrayList<Movement>());
        this.mMovementList.put(MovementType.HIP,new ArrayList<Movement>());
        this.mMovementList.put(MovementType.COMBINED,new ArrayList<Movement>());
    }

    @Override
    public String javaObjectToJson() {
        String json = mGson.toJson(this.mMovementList);
        return json;
    }

    @Override
    public void jsonToJavaObject() {
        Type MovementListType = new TypeToken<HashMap<MovementType,ArrayList<Movement>>>(){}.getType();
        this.mMovementList = mGson.fromJson(this.loadedJson, MovementListType);
    }

    public Map<MovementType,List<Movement>>  getMovementList() {
        return mMovementList;
    }
}
