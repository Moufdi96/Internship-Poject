package com.example.pepperapp.model;

import android.net.Uri;

public class Movement {

    private String mMovementId;
    private String mMovementName;
    private MovementType mMovementType;
    private String mMovementDescription;
    private String mUri;
    //private List<HashMap<JointType, Float>> mMove;

    public Movement(String mMovementId, String mMovementName, MovementType mMovementType, String mMovementDescription,String uri) {
        this.mMovementId = mMovementId;
        this.mMovementName = mMovementName;
        this.mMovementType = mMovementType;
        this.mMovementDescription = mMovementDescription;
        this.mUri = uri;
        //this.mMove = new ArrayList<>();
    }

    public String getmMovementName() {
        return mMovementName;
    }

    public void setmMovementName(String mMovementName) {
        this.mMovementName = mMovementName;
    }

    public MovementType getmMovementType() {
        return mMovementType;
    }

    public void setmMovementType(MovementType mMovementType) {
        this.mMovementType = mMovementType;
    }

    public String getmMovementDescription() {
        return mMovementDescription;
    }


    public void setmMovementDescription(String mMovementDescription) {
        this.mMovementDescription = mMovementDescription;
    }

    public String getmMovementId() {
        return mMovementId;
    }

    public void setmMovementId(String mMovementId) {
        this.mMovementId = mMovementId;
    }

    public String getmURI() {
        return mUri;
    }

    public void setmURI(String mURI) {
        this.mUri = mURI;
    }
}
