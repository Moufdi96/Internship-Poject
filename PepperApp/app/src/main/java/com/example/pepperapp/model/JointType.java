package com.example.pepperapp.model;

public enum JointType {
    PHIP("HipPitch"), RHIP("HipRoll"), PNECK("HeadPitch"), YNECK("HeadYaw"), PLSHOULDER("LShoulderPitch"),
    RLSHOULDER("LShoulderRoll"), PRSHOULDER("RShoulderPitch"), RRSHOULDER("RShoulderRoll"), YLWRIST("LWristYaw"),
    YRWRIST("RWristYaw"), YLELBOW("LElbowYaw"), RLELBOW("LElbowRoll"), YRELBOW("RElbowYaw"),
    RRELBOW("RElbowRoll"), LFIST("LHand"), RFIST("RHand"), KNEEPITCH("KneePitch");

    String jointName = ""; //the real name of the joint according to Naoqi OS

    JointType(String jointName) {
        this.jointName = jointName;
    }

    public String getJointName() {
        return jointName;
    }
}
