package com.example.pepperapp.model;

import java.util.ArrayList;
import java.util.List;

public class Activity {

    private String mActivityName;
    private String mActivityDescription;
    private List<Movement> mActivityMovements;

    public Activity(String mActivityName, String mActivityDescription, List<Movement> mActivityMovements) {
        this.mActivityName = mActivityName;
        this.mActivityDescription = mActivityDescription;
        this.mActivityMovements = mActivityMovements;
    }

    public String getmActivityName() {
        return mActivityName;
    }

    public void setmActivityName(String mActivityName) {
        this.mActivityName = mActivityName;
    }

    public String getmActivityDescription() {
        return mActivityDescription;
    }

    public void setmActivityDescription(String mActivityDescription) {
        this.mActivityDescription = mActivityDescription;
    }

    public List<Movement> getmActivityMovements() {
        return mActivityMovements;
    }

    public void setmActivityMovements(List<Movement> mActivityMovements) {
        this.mActivityMovements = mActivityMovements;
    }

    public void removeMovementFromActivity(int Id){ //id = index in the movement list
        mActivityMovements.remove(Id);
    }

    public void addMovementToActivity(Movement movement){
        mActivityMovements.add(movement);
    }

    public void addMovementToActivity(int movementId,String movementName,MovementType movementType,String movementDescription){
        mActivityMovements.add(new Movement(movementId,movementName,movementType,movementDescription));
    }
}
