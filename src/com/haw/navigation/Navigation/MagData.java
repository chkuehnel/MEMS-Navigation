package com.haw.navigation.Navigation;

/**
 * Created by chkue_000 on 28.09.2014.
 */
public class MagData {

    private float xMagData;
    private float yMagData;
    private float zMagData;

    public MagData(float xMagData, float yMagData, float zMagData) {
        this.xMagData = xMagData;
        this.yMagData = yMagData;
        this.zMagData = zMagData;
    }

    public float getxMagData() {
        return xMagData;
    }

    public void setxMagData(float xMagData) {
        this.xMagData = xMagData;
    }

    public float getyMagData() {
        return yMagData;
    }

    public void setyMagData(float yMagData) {
        this.yMagData = yMagData;
    }

    public float getzMagData() {
        return zMagData;
    }

    public void setzMagData(float zMagData) {
        this.zMagData = zMagData;
    }
}
