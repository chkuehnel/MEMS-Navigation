package com.haw.navigation.Navigation;

/**
 * Created by chkue_000 on 28.09.2014.
 */
public class GyroData {
    private float xGyroData;
    private float yGyroData;
    private float zGyroData;

    public GyroData(float xGyroData, float yGyroData, float zGyroData) {
        this.xGyroData = xGyroData;
        this.yGyroData = yGyroData;
        this.zGyroData = zGyroData;
    }

    public float getxGyroData() {
        return xGyroData;
    }

    public void setxGyroData(float xGyroData) {
        this.xGyroData = xGyroData;
    }

    public float getyGyroData() {
        return yGyroData;
    }

    public void setyGyroData(float yGyroData) {
        this.yGyroData = yGyroData;
    }

    public float getzGyroData() {
        return zGyroData;
    }

    public void setzGyroData(float zGyroData) {
        this.zGyroData = zGyroData;
    }
}
