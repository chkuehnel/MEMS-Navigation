package com.haw.navigation.Navigation;

/**
 * Created by chkue_000 on 28.09.2014.
 */
public class AccData {
    private float xAccData;
    private float yAccData;
    private float zAccData;

    public AccData(float xAccData, float yAccData, float zAccData) {
        this.xAccData = xAccData;
        this.yAccData = yAccData;
        this.zAccData = zAccData;
    }

    public float getxAccData() {
        return xAccData;
    }

    public void setxAccData(float xAccData) {
        this.xAccData = xAccData;
    }

    public float getyAccData() {
        return yAccData;
    }

    public void setyAccData(float yAccData) {
        this.yAccData = yAccData;
    }

    public float getzAccData() {
        return zAccData;
    }

    public void setzAccData(float zAccData) {
        this.zAccData = zAccData;
    }
}
