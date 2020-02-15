/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.TimeOfFlight;

public class GetTofDistance extends Command {
  
  OI oi;
  TimeOfFlight timeofflight;

  public GetTofDistance() {
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    oi = Robot.oi;
    timeofflight = Robot.timeofflight;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //System.out.print(timeofflight.getDistance3());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {

  }

  @Override
  public void interrupted() {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
