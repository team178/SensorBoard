/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

import java.nio.DoubleBuffer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.letsbuildrockets.libs.TimeOfFlightSensor;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class TimeOfFlight extends SubsystemBase {

  private TimeOfFlightSensor tof1;
  private TimeOfFlightSensor tof2;
  private VictorSPX motor;
  private int counter;
  private boolean inTrigger;
  private boolean outTrigger;

  public TimeOfFlight() {
    tof1 = new TimeOfFlightSensor(0x0620);
    tof2 = new TimeOfFlightSensor(0x0621);
    motor = new VictorSPX(RobotMap.motor1);
    counter = 0;
    inTrigger = true;
    outTrigger = false;
  }

  public double getDistance1() {
    return tof1.getD();
  }

  public double getDistance2() {
    return tof2.getD();
  }

  public String getEdge1() {
    return tof1.getEdge();
  }

  public String getEdge2() {
    return tof2.getEdge();
  }

  public void addToCounter() {
    if (tof1.getEdge() == "Leading" && inTrigger) {
      counter++;
      inTrigger = false;
    }
    if (tof1.getEdge() == "No ball" && !inTrigger) {
      inTrigger = true;
    }
  }

  public void removeFromCounter() {
    if (tof2.getEdge() == "Leading" && !outTrigger) {
      outTrigger = true;
    }
    if (tof2.getEdge() == "No ball" && outTrigger) {
      counter--;
      outTrigger = false;
    }
  }

  public String moveMotor() {
    if (getEdge1() == "Leading") {
      motor.set(ControlMode.PercentOutput, 1);
      return "Moving";
    } else if (getEdge2() == "Trailing") {
      motor.set(ControlMode.PercentOutput, 0);
      System.out.println("stopped");
      return "Not Moving";
    }
    return "Previous State";
  }

  public int getCounter() {
    return counter;
  }

  @Override
  public void periodic() {
    
  }
}