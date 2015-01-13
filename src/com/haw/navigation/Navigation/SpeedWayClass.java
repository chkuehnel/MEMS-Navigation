package com.haw.navigation.Navigation;

/**
 * Created by chkue_000 on 07.11.2014.
 */
public class SpeedWayClass {

    private double oldSpeedX = 0;
    private double oldSpeedY = 0;
    private double oldSpeedZ = 0;
    private long oldTime = 0;

    private double accX;
    private double accY;
    private double accZ;
    private SpeedWayData newSpeedWay;

    private double oldWayX;
    private double oldWayY;
    private double oldWayZ;

    private  double[][] DCM;
    double[] correctedAcc = new double[3];

    private final double g = 9.81;

    private ResultAvailableListener listener;

    public SpeedWayClass(ResultAvailableListener listener) {
        this.listener = listener;
        newSpeedWay = new SpeedWayData();
        oldTime = System.nanoTime();
    }

    public void computeWay(AccData accData, FixedAngle angle, double[][] DCM) {
        System.out.println("computeWay called");
        this.accX = accData.getxAccData() * g;
        this.accY = accData.getyAccData() * g;
        this.accZ = accData.getzAccData() * g;
        this.DCM = DCM;

        correctAccData();
        integrateAcc();
        // TODO: transfer this call to speedwayclass
    }

    private void integrateAcc() {
        float divider = (float) Math.pow(10, 9);
        long newTime = System.nanoTime();
        float deltaT = newTime - oldTime;
        float delta = (deltaT) / divider;

        newSpeedWay.setSpeedX(oldSpeedX + delta*correctedAcc[0]);
        newSpeedWay.setSpeedY(oldSpeedY + delta*correctedAcc[1]);
        newSpeedWay.setSpeedZ(oldSpeedZ + delta*correctedAcc[2]);

        newSpeedWay.setWayX(oldWayX + delta*oldSpeedX);
        newSpeedWay.setWayY(oldWayY + delta*oldSpeedY);
        newSpeedWay.setWayZ(oldWayZ + delta*oldSpeedZ);

        oldSpeedX = newSpeedWay.getSpeedX();
        oldSpeedY = newSpeedWay.getSpeedY();
        oldSpeedZ = newSpeedWay.getSpeedZ();

        oldWayX = newSpeedWay.getWayX();
        oldWayY = newSpeedWay.getWayY();
        oldWayZ = newSpeedWay.getWayZ();

        oldTime = System.nanoTime();
    }

    public void correctAccData(){
        // TODO: source??
        correctedAcc[0] = DCM[0][0]*accX + DCM[0][1]*accY + DCM[0][2]*accZ;
        correctedAcc[1] = DCM[1][0]*accX + DCM[1][1]*accY + DCM[1][2]*accZ;
        correctedAcc[2] = DCM[2][0]*accX + DCM[2][1]*accY + DCM[2][2]*accZ + g;

    }

    public interface ResultAvailableListener{
        public void resultAvailable();
    }

    public SpeedWayData getNewSpeedWay() {
        return newSpeedWay;
    }
}
