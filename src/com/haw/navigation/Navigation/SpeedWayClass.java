package com.haw.navigation.Navigation;

/**
 * Created by chkue_000 on 07.11.2014.
 */
public class SpeedWayClass {

    private double oldAccX = 0;
    private double oldAccY = 0;
    private double oldAccZ = 0;
    private double oldSpeedX;
    private double oldSpeedY;
    private double oldSpeedZ;
    private long oldTime;

    private double accX;
    private double accY;
    private double accZ;
    private FixedAngle angle;

    private SpeedWayData oldSpeedWay;
    private SpeedWayData newSpeedWay;

    private double speedX;
    private double speedY;
    private double speedZ;

    private double wayX;
    private double wayY;
    private double wayZ;
    private double oldWayX;
    private double oldWayY;
    private double oldWayZ;

    private final double g = 9.81;

    private ResultAvailableListener listener;

    public SpeedWayClass(ResultAvailableListener listener) {
        this.listener = listener;
        oldSpeedWay = new SpeedWayData(0, 0, 0, 0, 0, 0);
        newSpeedWay = new SpeedWayData();
    }

    public void computeWay(AccData accData, FixedAngle angle) {
        this.accX = accData.getxAccData() * g;
        this.accY = accData.getyAccData() * g;
        this.accZ = accData.getzAccData() * g;
        this.angle = angle;

 //       correctAccData();
        integrateAcc();

        // TODO: transfer this call to speedwayclass
        listener.resultAvailable();
        System.out.println("computeWay called");
    }

    private void integrateAcc() {
        long delta = System.nanoTime() - oldTime / ((long) Math.pow(10, 9));

        newSpeedWay.setSpeedX(oldSpeedWay.getSpeedX() + (delta * (oldAccX - accX)) / 2);
        newSpeedWay.setSpeedY(oldSpeedWay.getSpeedY() + (delta * (oldAccY - accY)) / 2);
        newSpeedWay.setSpeedZ(oldSpeedWay.getSpeedZ() + (delta * (oldAccZ - accZ)) / 2);

        newSpeedWay.setWayX(oldSpeedWay.getWayX() + (delta * (oldSpeedWay.getSpeedX() - newSpeedWay.getSpeedX()) / 2));
        newSpeedWay.setWayY(oldSpeedWay.getWayY() + (delta * (oldSpeedWay.getSpeedY() - newSpeedWay.getSpeedY()) / 2));
        newSpeedWay.setWayZ(oldSpeedWay.getWayZ() + (delta * (oldSpeedWay.getSpeedZ() - newSpeedWay.getSpeedZ()) / 2));

        oldTime = System.nanoTime();
    }

    public void correctAccData(){
        accX = accX - angle.getNormPsi() * g;
        accY = accY - angle.getNormTheta() * g;
        accZ = accZ - angle.getNormPhi() * g;
    }

    public interface ResultAvailableListener{
        public void resultAvailable();
    }

    public SpeedWayData getNewSpeedWay() {
        return newSpeedWay;
    }
}
