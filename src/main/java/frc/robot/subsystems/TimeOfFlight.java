/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Ultrasonic;

import frc.robot.RobotMap;
import frc.robot.commands.GetTofDistance;

public class TimeOfFlight extends Subsystem {
  
  Ultrasonic tof;

  public TimeOfFlight() {
    tof = new Ultrasonic(RobotMap.tofOutput, RobotMap.tofInput);
  }

  public double getDistance() {
    return tof.getRangeInches();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new GetTofDistance());
  }
}
