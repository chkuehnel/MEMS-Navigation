package com.haw.navigation.Navigation;

import java.util.DoubleSummaryStatistics;

/**
 * Created by chkue_000 on 17.10.2014.
 */
public class QuaternionClass {
    double roll_deg = 0;     //x
    double pitch_deg = 6;    //y
    double yaw_deg = 45;      //z
    double roll_rad;     //x
    double pitch_rad;    //y
    double yaw_rad;      //z
    double[][] DCM = new double[3][3];
    Quaternion quaternion;

    ResultAvailableListener listener;

    public QuaternionClass(float roll_deg, float pitch_deg, float yaw_deg, float roll_rad, float pitch_rad, float yaw_rad) {
        this.roll_deg = roll_deg;
        this.pitch_deg = pitch_deg;
        this.yaw_deg = yaw_deg;
        this.roll_rad = roll_rad;
        this.pitch_rad = pitch_rad;
        this.yaw_rad = yaw_rad;
    }

    public QuaternionClass(float roll_deg, float pitch_deg, float yaw_deg) {
        this.roll_deg = roll_deg;
        this.pitch_deg = pitch_deg;
        this.yaw_deg = yaw_deg;
    }

    public QuaternionClass(ResultAvailableListener listener) {
        this.listener = listener;
        quaternion = new Quaternion();
    }



    public void fillDCM(GyroData gyroData) {
        convertToRad(gyroData);
        computeQuaternion();
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


    private void computeDCM() {
        double q1 = quaternion.getQ1();
        double q2 = quaternion.getQ2();
        double q3 = quaternion.getQ3();
        double q4 = quaternion.getQ4();

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

    public interface ResultAvailableListener{
        public void resultAvailable();
    }

    public Quaternion getQuaternion() {
        return quaternion;
    }
}

