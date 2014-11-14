package com.haw.navigation.Navigation;

/**
 * Tilt-compensated eCompass
 * Update for 10s and hold device still
 *
 * Created by Florian on 14.11.2014.
 */
public class ECompass {

    private long calls;
    private double compass;
    private float mx,my,mz;
    private float angleX,angleY,angleZ;

    public ECompass() {
        this.calls = 0;
    }

    /**
     * Calculate heading from magnetometer and accelerometer data with low pass filter.
     * @param m
     * @param g
     */
    public void updateCompass(MagData m, GyroData g) {
        calls = calls + 1; // update number of calls

        mx = m.getxMagData(); // get raw data
        my = m.getyMagData();
        mz = m.getzMagData();
        angleX = g.getxGyroData();
        angleY = g.getyGyroData();
        angleZ = g.getzGyroData();

        compass = 0.93 * compass + 0.07 * calculate(); // low pass filter
    }

    /**
     * Freescale Semiconductor, Application Note 4248,
     * Implementing a Tilt-Compensated eCompass using Accelerometer and Magnetometer Sensors
     * @return calculated heading from magnetometer and angles
     */
    private double calculate() {
        return Math.atan2((-my * Math.cos(angleX) + mz * Math.sin(angleX)),
                (mx * Math.cos(angleY) + my * Math.sin(angleY) * Math.sin(angleX) + mz * Math.sin(angleY) * Math.cos(angleX)));
    }
}
