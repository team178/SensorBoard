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
 * @author Robit Fisheljee
 * @version 1.0
 * @since 2020.1.2
 * 
 */

public class TimeOfFlight extends SubsystemBase {

  private TimeOfFlightSensor tof1;
  private TimeOfFlightSensor tof2;
  private TimeOfFlightSensor tof3;
  private VictorSPX lawnMower;
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
    lawnMower = new VictorSPX(RobotMap.motor1);
    counter = 0;
    inTrigger = true;
    outTrigger = false;
  }

  public void ballDump(double speed) {
    if (getCounter() != 0) {
      lawnMower.set(ControlMode.PercentOutput, speed);
    } else {
      lawnMower.set(ControlMode.PercentOutput, 0);
    }
  }

  public void runMower(double speed) {
    if (getCounter() < 4) {
      if (!tof1.getEdge().equals("No ball")) {
        lawnMower.set(ControlMode.PercentOutput, speed);
      } else if (tof2.getEdge().equals("Center")) {
        lawnMower.set(ControlMode.PercentOutput, 0);
      }
    } else {
      lawnMower.set(ControlMode.PercentOutput, 0);
    }
  }

/**
  * @return Distance for the first sensor
  */

  public double getDistance1() {
    System.out.println(tof1.getD());
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
    if (tof1.getEdge().equals("Leading") && inTrigger) {
      counter++;
      inTrigger = false;
    }
    if (tof1.getEdge().equals("No ball") && !inTrigger) {
      inTrigger = true;
    }
  }

/**
 * Determines where the ball is using the sensor
 * @return String either indicating forward movement or no direction
 */

  public String ballMovement() {
    String direction = "No Direction";
    if (tof1.getEdge().equals("Trailing") && tof2.getEdge().equals("Leading")) {
       direction = "Forwards";
    } else if (tof2.getEdge().equals("Trailing") && tof1.getEdge().equals("Leading")) {
       direction = "Backwards";
    }
    return direction;
  }

 /**
   * Method to decrement counter variable when ball has left range from the third sensor
   */

  public void removeFromCounter() {
    if (!(tof3.getEdge().equals("No ball")) && !outTrigger) {
      outTrigger = true;
    }
    if (tof3.getEdge().equals("No ball") && outTrigger) {
      counter--;
      outTrigger = false;
    }
  }
  /**
   * @return Integer for the counter value
   */

  public int getCounter() {
    addToCounter();
    removeFromCounter();
    counterFixer();
    return counter;
  }

  public void counterFixer() {
    while (counter < 0) {
      counter ++;
    }
  }

  public String maximumCapacity() {
    String capacity = "Not full yet";
    if (tof3.getEdge().equals("Center")) {
      capacity = "Maximum capacity!";
    }
    return capacity;
  }

  /*
    TO-DO (updated 2/8/20):
    
    - Make limit switch control buttonPressed (or wasButtonPressed) in moveMotorNew method
    - Make motor on Sensor Board MOVE based off of the status of variable "status" in moveMotorNew method
    - Make a dumpBall method, activated by a separate button, that runs the motor 
      - if not max capacity, run until next ball triggers max capacity, then run the motor again, etc. (increments) 
    - Shift down time of flight, for clearance stop ball at trailing edge of second ToF
    (updated 2/10/2020):
    - turn comments into actual code for the button to enable/disable the motor
    - turn comments into actual code for the dumpball method
    - I'm :/ - liza
  */
  }
