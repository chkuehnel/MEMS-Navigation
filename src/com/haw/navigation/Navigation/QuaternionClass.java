package com.haw.navigation.Navigation;

/**
 * Created by chkue_000 on 17.10.2014.
 */
public class QuaternionClass {
    private double roll_rad;     //x
    private double pitch_rad;    //y
    private double yaw_rad;      //z
    private double[][] DCM = new double[3][3];
    private Quaternion quaternion;
    private FixedAngle angleData;

    private ResultAvailableListener listener;

    public QuaternionClass(ResultAvailableListener listener) {
        this.listener = listener;
        quaternion = new Quaternion();
    }

    public void fillDCM(GyroData gyroData) {
        convertToRad(gyroData);
        computeQuaternion();
        computeAngle(quaternion);
        computeDCM();
        listener.resultAvailable();
    }

    private void convertToRad(GyroData gyroData) {
        roll_rad = gyroData.getxGyroData() * (float) (Math.PI / 180);
        pitch_rad = gyroData.getyGyroData() * (float) (Math.PI / 180);
        yaw_rad = gyroData.getzGyroData() * (float) (Math.PI / 180);
    }

    private void computeQuaternion() {
        Double q1 = (Math.cos(yaw_rad / 2) * Math.cos(pitch_rad / 2) * Math.cos(roll_rad / 2) + Math.sin(yaw_rad / 2) * Math.sin(pitch_rad / 2) * Math.sin(roll_rad / 2));
        Double q2 = (Math.cos(yaw_rad / 2) * Math.cos(pitch_rad / 2) * Math.sin(roll_rad / 2) - Math.sin(yaw_rad / 2) * Math.sin(pitch_rad / 2) * Math.cos(roll_rad / 2));
        Double q3 = (Math.cos(yaw_rad / 2) * Math.sin(pitch_rad / 2) * Math.cos(roll_rad / 2) + Math.sin(yaw_rad / 2) * Math.cos(pitch_rad / 2) * Math.sin(roll_rad / 2));
        Double q4 = (Math.sin(yaw_rad / 2) * Math.cos(pitch_rad / 2) * Math.cos(roll_rad / 2) - Math.cos(yaw_rad / 2) * Math.sin(pitch_rad / 2) * Math.sin(roll_rad / 2));
        quaternion.setAll(q1, q2, q3, q4);
    }

    private void computeAngle(Quaternion quaternion){
        double q0 = quaternion.getQ0();
        double q1 = quaternion.getQ1();
        double q2 = quaternion.getQ2();
        double q3 = quaternion.getQ3();

        double phi = - Math.atan((2*(q2*q3 + q0*q1))/(-q0*q0 + q1*q1 + q2*q2 - q3*q3))*180/Math.PI;
        double theta = Math.asin(2*(q0*q2 - q1*q3))*180/Math.PI;
        double psi = Math.atan((2*(q1*q2 + q0*q3))/(q0*q0 + q1*q1 - q2*q2 - q3*q3))*180/Math.PI;

        angleData = new FixedAngle(phi, theta, psi);
    }


    private void computeDCM() {
        double q0 = quaternion.getQ0();
        double q1 = quaternion.getQ1();
        double q2 = quaternion.getQ2();
        double q3 = quaternion.getQ3();

        DCM[0][0] = ((q0 * q0) - (q1 * q1) - (q2 * q2) + (q3 * q3));
        DCM[0][1] = (2 * ((q0 * q1) + (q2 * q3)));
        DCM[0][2] = (2 * ((q0 * q2) - (q1 * q3)));

        DCM[1][0] = (2 * ((q0 * q1) - (q2 * q3)));
        DCM[1][1] = (-(q0 * q0) + (q1 * q1) - (q2 * q2) + (q3 * q3));
        DCM[1][2] = (2 * ((q1 * q2) - (q0 * q3)));

        DCM[2][0] = (2 * ((q0 * q2) + (q1 * q3)));
        DCM[2][1] = (2 * ((q1 * q2) + (q0 * q3)));
        DCM[2][2] = (-(q0 * q0) - (q1 * q1) + (q2 * q2) + (q3 * q3));
    }

    public interface ResultAvailableListener{
        public void resultAvailable();
    }

    public Quaternion getQuaternion() {
        return quaternion;
    }

    public FixedAngle getAngleData() {
        return angleData;
    }
}

