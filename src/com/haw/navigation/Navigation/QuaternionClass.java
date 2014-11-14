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

    public QuaternionClass() {
        quaternion = new Quaternion();
    }

    public void fillDCM(GyroData gyroData) {
        convertToRad(gyroData);
        computeQuaternion();
        computeAngle(quaternion);
        computeDCM();
        System.out.println("fillDcm");
    }

    private void convertToRad(GyroData gyroData) {
        roll_rad = gyroData.getxGyroData() * (float) (Math.PI / 180);
        pitch_rad = gyroData.getyGyroData() * (float) (Math.PI / 180);
        yaw_rad = gyroData.getzGyroData() * (float) (Math.PI / 180);
    }

    private void computeQuaternion() {
        Double q0 = (Math.cos(yaw_rad / 2) * Math.cos(pitch_rad / 2) * Math.cos(roll_rad / 2) + Math.sin(yaw_rad / 2) * Math.sin(pitch_rad / 2) * Math.sin(roll_rad / 2));
        Double q1 = (Math.cos(yaw_rad / 2) * Math.cos(pitch_rad / 2) * Math.sin(roll_rad / 2) - Math.sin(yaw_rad / 2) * Math.sin(pitch_rad / 2) * Math.cos(roll_rad / 2));
        Double q2 = (Math.cos(yaw_rad / 2) * Math.sin(pitch_rad / 2) * Math.cos(roll_rad / 2) + Math.sin(yaw_rad / 2) * Math.cos(pitch_rad / 2) * Math.sin(roll_rad / 2));
        Double q3 = (Math.sin(yaw_rad / 2) * Math.cos(pitch_rad / 2) * Math.cos(roll_rad / 2) - Math.cos(yaw_rad / 2) * Math.sin(pitch_rad / 2) * Math.sin(roll_rad / 2));
        quaternion.setAll(q0, q1, q2, q3);
    }

    private void computeAngle(Quaternion quaternion){
        double q0 = quaternion.getQ0();
        double q1 = quaternion.getQ1();
        double q2 = quaternion.getQ2();
        double q3 = quaternion.getQ3();

        double phi = (Math.atan2((2*(q0*q1 + q2*q3)), (1 - 2*(q1*q1 + q2*q2) ))) *180/Math.PI;
        double theta = Math.atan(2*(q0*q2 - q3*q1)) *180/Math.PI;
        double psi = Math.atan2((2*(q0*q3 + q1*q2)),(1 - 2*(q2*q2 + q3*q3))) *180/Math.PI;

        angleData = new FixedAngle(phi, theta, psi);
    }


    private void computeDCM() {
        double q1 = quaternion.getQ0();
        double q2 = quaternion.getQ1();
        double q3 = quaternion.getQ2();
        double q4 = quaternion.getQ3();

        DCM[0][0] = ((q1 * q1) - (q2 * q2) - (q3 * q3) + (q4 * q4));
        DCM[0][1] = (2 * ((q1 * q2) + (q3 * q4)));
        DCM[0][2] = (2 * ((q1 * q3) - (q2 * q4)));

        DCM[1][0] = (2 * ((q1 * q2) - (q3 * q4)));
        DCM[1][1] = (-(q1 * q1) + (q2 * q2) - (q3 * q3) + (q4 * q4));
        DCM[1][2] = (2 * ((q2 * q3) - (q1 * q4)));

        DCM[2][0] = (2 * ((q1 * q3) + (q2 * q4)));
        DCM[2][1] = (2 * ((q2 * q3) + (q1 * q4)));
        DCM[2][2] = (-(q1 * q1) - (q2 * q2) + (q3 * q3) + (q4 * q4));
    }



    public Quaternion getQuaternion() {
        return quaternion;
    }

    public FixedAngle getAngleData() {
        return angleData;
    }
}

