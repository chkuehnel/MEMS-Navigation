package com.haw.navigation.Navigation;

/**
 * Created by chkue_000 on 07.11.2014.
 */
public class SpeedWayData {

    private double speedX;
    private double speedY;
    private double speedZ;

    private double wayX;
    private double wayY;
    private double wayZ;

    public SpeedWayData(double speedX, double speedY, double speedZ, double wayX, double wayY, double wayZ) {
        this.speedX = speedX;
        this.speedY = speedY;
        this.speedZ = speedZ;
        this.wayX = wayX;
        this.wayY = wayY;
        this.wayZ = wayZ;
    }

    public SpeedWayData() {
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public double getSpeedZ() {
        return speedZ;
    }

    public void setSpeedZ(double speedZ) {
        this.speedZ = speedZ;
    }

    public double getWayX() {
        return wayX;
    }

    public void setWayX(double wayX) {
        this.wayX = wayX;
    }

    public double getWayY() {
        return wayY;
    }

    public void setWayY(double wayY) {
        this.wayY = wayY;
    }

    public double getWayZ() {
        return wayZ;
    }

    public void setWayZ(double wayZ) {
        this.wayZ = wayZ;
    }
}
