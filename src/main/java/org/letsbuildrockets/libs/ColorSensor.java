/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.letsbuildrockets.libs;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

/**
 * Add your docs here.
 */
public class ColorSensor {
    private final I2C.Port i2cport = I2C.Port.kOnboard;
    private ColorSensorV3 colorsensor;

    public ColorSensor() {
        colorsensor = new ColorSensorV3(i2cport);
    }

    public Color detectColor() {
        return colorsensor.getColor();
    }

    public double getRed() {
        return detectColor().red;
    }

    public double getGreen() {
        return detectColor().green;
    }

    public double getBlue() {
        return detectColor().blue;
    }

    public int getProximity() {
        return colorsensor.getProximity();
    }

    public int getIR() {
        return colorsensor.getIR();
    }

    public void printRgbValues() {
        
    }
}