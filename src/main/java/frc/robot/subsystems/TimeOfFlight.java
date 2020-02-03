/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.nio.DoubleBuffer;
import org.letsbuildrockets.libs.TimeOfFlightSensor;

import edu.wpi.first.wpilibj.TimedRobot;



public class TimeOfFlight extends SubsystemBase {

  private TimeOfFlightSensor tofsensor;
  private double[] values;
  private String lastEdge;
  public final double MAX = 150;
  public final double MIN = 60;

  public TimeOfFlight(int port) {
    tofsensor = new TimeOfFlightSensor(port);
    System.out.println("ToF Sensor at " + String.format("0x%03x", tofsensor.getID()) + "! (with firmware version: " + tofsensor.getFirwareVersion().toString()+")");
    values = new double[2]; 
    lastEdge = "None";
  }

  public double getDistance() {
    values[1] = values[0];
    values[0] = tofsensor.getDistance();
    return values[0];
  }

  public String getEdge() {
    double secant = (values[1] - values[0])/0.02;
    //System.out.println("Slope of Secant Line: " + secant);
    if (values[0] > MAX) { //test this max value
      return "No ball";
    } else if (values[0] < MIN) { //test this min value
      return "Center";
    } else if (secant > 100) {
      lastEdge = "Leading";
      return "Leading";
    } else if (secant < -100) {
      lastEdge = "Trailing";
      return "Trailing";
    } else if (lastEdge == "Leading") {
      return "Leading";
    } else if (lastEdge == "Trailing") {
      return "Trailing";
    } else if (lastEdge == "None") {
      return "No ball";
    }
    return "No ball";
    //A minimum value returns "Center", a maximum value returns "No Ball"
  }
}