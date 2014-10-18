package com.haw.navigation.Navigation;

/**
 * Created by Tower on 06.10.2014.
 */
public class AngleData {

    private float yawAngle;
    private float pitchAngle;
    private float rollAngle;

    public AngleData(float rollAngle, float pitchAngle, float yawAngle) {
        this.rollAngle = rollAngle;
        this.pitchAngle = pitchAngle;
        this.yawAngle = yawAngle;
    }

    public float getYawAngle() {
        return yawAngle;
    }

    public AngleData() {
    }

    public void setYawAngle(float yawAngle) {
        this.yawAngle = yawAngle;
    }

    public float getPitchAngle() {
        return pitchAngle;
    }

    public void setPitchAngle(float pitchAngle) {
        this.pitchAngle = pitchAngle;
    }

    public float getRollAngle() {
        return rollAngle;
    }

    public void setRollAngle(float rollAngle) {
        this.rollAngle = rollAngle;
    }
}
