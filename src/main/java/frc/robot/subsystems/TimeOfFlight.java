/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import org.letsbuildrockets.libs.TimeOfFlightSensor;

/**
 * @author Robbie Fishel
 * @author Ronit Banerjee
 * @version 1.0
 * @since 2020.1.2
 * 
 */

public class TimeOfFlight extends SubsystemBase {

  private TimeOfFlightSensor tof1;
  private TimeOfFlightSensor tof2;
  private TimeOfFlightSensor tof3;
  private VictorSPX motor;
  protected int counter;
  private boolean inTrigger;
  private boolean outTrigger;


/**
 * Took the CAN adress on sensor to find these values
 */
  public TimeOfFlight() {
    tof1 = new TimeOfFlightSensor(0x0620);   
    tof2 = new TimeOfFlightSensor(0x0621);
    tof3 = new TimeOfFlightSensor(0x0624); 
    motor = new VictorSPX(RobotMap.motor1); 
    counter = 0;
    inTrigger = true;
    outTrigger = false;
  }

  /**
  * @return Distance for the first sensor
  */
  public double getDistance1() {
    return tof1.getD();
  }

  /**
  * @return Distance for the second sensor
  */
  public double getDistance2() {
    return tof2.getD();
  }

  /**
  * @return Distance for the third sensor
  */
  public double getDistance3() {
    return tof3.getD();
  }

  /**
  * @return String for the ball leaving the edge of the first sensor
  */
  public String getEdge1() {
    return tof1.getEdge();
  }

  /**
  * @return String for the ball leaving the edge of the second sensor
  */
  public String getEdge2() {
    return tof2.getEdge();
  }

  /**
  * @return String for the ball leaving the edge of the third sensor
  */
  public String getEdge3() {
    return tof3.getEdge();
  }

  /**
  * Counter val depending on whether the ball is leading or if there is no ball
  * Using this int counter, the inTrigger boolean is set to a certain value, where it is then
  */
  public void addToCounter() {
    if (tof1.getEdge() == "Leading" && inTrigger) {
      counter++;
      inTrigger = false;
    }
    if (tof1.getEdge() == "No ball" && !inTrigger) {
      inTrigger = true;
    }
  }

/**
 * Determines where the ball is using the sensor
 * @return String either indicating forward movement or no direction
 */
  public String ballMovement() {
    String direction = null;
    if (tof1.getEdge() == "Trailing" && tof2.getEdge() == "Leading") {
       direction = "forwards";
    }
    try {
    return direction;
    } catch(java.lang.NullPointerException nPointerException) {
      return "No Direction";
    }
  }
  
  /**
   * Method to decrement counter variable when ball has left range from the third sensor
   */
  public void removeFromCounter() {
    if (tof3.getEdge() == "Leading" && !outTrigger) {
      outTrigger = true;
    }
    if (tof3.getEdge() == "No ball" && outTrigger) {
      counter--;
      outTrigger = false;
    }
  }

  /**
   * Method that runs motor based on ball location
   * @return String that indicates location of ball
   */
  public String moveMotor() {
    String state = "no ball";
    if (getEdge1() == "Leading") {
      motor.set(ControlMode.PercentOutput, 1);
      state = "Moving";
    } else if (getEdge2() == "Trailing") {
      motor.set(ControlMode.PercentOutput, 0);
      System.out.println("stopped");
      state = "Not Moving";
    }
    return state;
  }

  /**
   * @return Integer for the counter value
   */
  public int getCounter() {
    return counter;
  }
}