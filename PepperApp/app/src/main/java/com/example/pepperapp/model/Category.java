package com.example.pepperapp.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String mCategoryTitle;
    private MovementType mCategoryType;
    private List<Movement> mMovementList;

    public Category(MovementType mCategoryType) {
        this.mCategoryType = mCategoryType;
        this.mCategoryTitle = mCategoryType.getmCategoryTitle();
        this.mMovementList = new ArrayList<>();
    }

    public Category(MovementType mCategoryType,ArrayList<Movement> movementList) {
        this.mCategoryType = mCategoryType;
        this.mCategoryTitle = mCategoryType.getmCategoryTitle();
        this.mMovementList = movementList;
    }

    public String getmCategoryTitle() {
        return mCategoryTitle;
    }

    public void setmCategoryTitle(String mCategoryTitle) {
        this.mCategoryTitle = mCategoryTitle;
    }

    public MovementType getmCategoryType() {
        return mCategoryType;
    }

    public void setmCategoryType(MovementType mCategoryType) {
        this.mCategoryType = mCategoryType;
    }

    public List<Movement> getmMovementList() {
        return mMovementList;
    }

    public void setmMovementList(List<Movement> mMovementList) {
        this.mMovementList = mMovementList;
    }

    public void removeMovementFromCategory(int Id){ //id = index in the movement list
        mMovementList.remove(Id);
    }

    public void addMovementToCategory(Movement movement){
        mMovementList.add(movement);
    }

    public void addMovementToCategory(int movementId,String movementName,MovementType movementType,String movementDescription){
        mMovementList.add(new Movement(movementId,movementName,movementType,movementDescription));
    }


    /*public  ArrayList<Object> getMovementsNameList(){
        ArrayList<Object> movementsNameList = new ArrayList<>();
        for(int i = 0;i< this.mMovementList.size();i++){
            movementsNameList.add(this.getmMovementList().get(i).getmMovementName());
        }
        return movementsNameList;
    }*/
}
