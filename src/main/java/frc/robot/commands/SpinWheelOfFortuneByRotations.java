/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.WheelOfFortuneContestant;



public class SpinWheelOfFortuneByRotations extends Command {
 

  private static WheelOfFortuneContestant wheelOfFortuneContestant = Robot.wheelOfFortuneContestant;
  public static final double spinPower = 1;
  

  public SpinWheelOfFortuneByRotations() {
    addRequirements(Robot.wheelOfFortuneContestant);
  }

  // Called when the command is initially scheduled.
 
  private void addRequirements(WheelOfFortuneContestant wheelOfFortuneContestant2) {
  }

  @Override
  public void initialize() { //init wheeloffortunecontestant subsystem
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { //spins the wheel

    while(wheelOfFortuneContestant.getRot() < 3)
    {
      wheelOfFortuneContestant.spinToWin(spinPower);
    }
    
      wheelOfFortuneContestant.spinToWin(0);
    
  }

  // Called once the command ends or is interrupted.
  // @Override
  // public void end(boolean interrupted) {

  // }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() { //if rotations between 3-5 return true
  return false;
  }
}
