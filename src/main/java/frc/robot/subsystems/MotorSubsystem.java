/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.MoveMotor;

/**
 * Add your docs here.
 */
public class MotorSubsystem extends Subsystem {
  
  private VictorSPX motor1;
  private DigitalInput limitSwitch = new DigitalInput(RobotMap.limitSwitch);

  private DigitalInput bit1 = new DigitalInput(RobotMap.bit1);
  private DigitalInput bit2 = new DigitalInput(RobotMap.bit2);
  private DigitalInput bit3 = new DigitalInput(RobotMap.bit3);
  private DigitalInput bit4 = new DigitalInput(RobotMap.bit4);
  private DigitalInput[] bits = {bit1, bit2, bit3, bit4};

  public MotorSubsystem() {
    motor1 = new VictorSPX(RobotMap.motor1);
  }

  public void driveMotor(double power) {
    motor1.set(ControlMode.PercentOutput, power);
  }

  public boolean getLimitSwitch() {
    return limitSwitch.get();
  }

  public int getEncoderBinary() {
    String binary = "";
    for (DigitalInput bit : bits) {
      if (bit.get()) {
        binary += "1";
      } else {
        binary += "0";
      }
    }
    return Integer.parseInt(binary);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MoveMotor());
  }
}
