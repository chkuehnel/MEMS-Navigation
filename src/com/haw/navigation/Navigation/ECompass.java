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
    private double mx,my,mz;
    private double angleX,angleY,angleZ;
    private double xOffset =  0.1457;
    private double yOffset =  -0.7766;

    public ECompass() {
        this.calls = 0;
    }

    public long getCalls() {
        return calls;
    }

    public double getCompass() {
        return compass;
    }

    public void setCalls(long calls) {
        this.calls = calls;
    }

    public void setCompass(MagData m, GyroData g) {
        mx = m.getxMagData() - 0.1457; // get raw data
        my = m.getyMagData();
        mz = m.getzMagData();
        angleX = g.getxGyroData();
        angleY = g.getyGyroData();
        angleZ = g.getzGyroData();

        this.compass = calculate(); // calculate heading
    }

    /**
     * Calculate heading from magnetometer and accelerometer data with low pass filter.
     * @param m
     * @param g
     */
    public void updateCompass(MagData m, GyroData g) {
        calls = calls + 1; // update number of calls

        mx = m.getxMagData() - xOffset; // get raw data with hard iron compensation
        my = m.getyMagData() - yOffset;
        mz = m.getzMagData();
        angleX = g.getxGyroData();
        angleY = g.getyGyroData();
        angleZ = g.getzGyroData();

        compass = 0.99 * compass + 0.01 * calculate(); // low pass filter

        // keep compass values between -180 .. 180
        if (compass > Math.PI)
            compass -= 2*Math.PI;
        if (compass < -Math.PI)
            compass += 2*Math.PI;

        // Once you have your heading, you must then add your 'Declination Angle',
        // which is the 'Error' of the magnetic field in your location.
        // Find yours here: http://www.magnetic-declination.com/

    }

    /**
     * http://www.freescale.com/files/sensors/doc/app_note/AN4246.pdf
     * Freescale Semiconductor, Application Note 4248,
     * Implementing a Tilt-Compensated eCompass using Accelerometer and Magnetometer Sensors
     * @return calculated heading from magnetometer and angles
     */
    private double calculate() {
        return Math.atan2((-my * Math.cos(angleX) + mz * Math.sin(angleX)),
                (mx * Math.cos(angleY) + my * Math.sin(angleY) * Math.sin(angleX) + mz * Math.sin(angleY) * Math.cos(angleX)));
    }
}
