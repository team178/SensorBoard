/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.letsbuildrockets.libs.TimeOfFlightSensor;

import edu.wpi.first.wpilibj.TimedRobot;

public class TimeOfFlight extends SubsystemBase {

  private TimeOfFlightSensor tofsensor;
  /**
   * Creates a new Timeofflight.
   */
  public void robotInit() {
    tofsensor = new TimeOfFlightSensor(0x621);
    System.out.println("ToF Sensor at " + String.format("0x%03x", tofsensor.getID()) + "! (with firmware version: " + tofsensor.getFirwareVersion().toString()+")");
  }

  public void getDistance() {
    // This method will be called once per scheduler run
    if(tofsensor.inRange()){
      System.out.println("distance: " + tofsensor.getDistance()+ " " + tofsensor.getError());
      // distance measured in mm
      if(tofsensor.getDistance() <= 600){
        boolean ballHere = true;
      }
    } else {
      System.out.println("out of range");
    }
  }
}

// websites used for background understandng:
// https://docs.wpilib.org/en/latest/docs/software/sensors/encoders-software.html
// https://first.wpi.edu/FRC/roborio/beta/docs/java/edu/wpi/first/wpilibj/Encoder.html