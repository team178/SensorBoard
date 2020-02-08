/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.WheelOfFortuneContestant;
import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.TimeOfFlight;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static OI oi;
  public static MotorSubsystem motorSubsystem;
  public static TimeOfFlight timeofflight;
  public static WheelOfFortuneContestant contestant;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    oi = new OI();
    motorSubsystem = new MotorSubsystem();
    timeofflight = new TimeOfFlight();
    contestant = new WheelOfFortuneContestant();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    timeofflight.addToCounter();
    timeofflight.removeFromCounter();
    SmartDashboard.putNumber("TOF 1 Distance", timeofflight.getDistance1());
    SmartDashboard.putNumber("TOF 2 Distance", timeofflight.getDistance2());
    SmartDashboard.putNumber("TOF 3 Distance", timeofflight.getDistance3());
    SmartDashboard.putNumber("Number of Balls In Mechanism", timeofflight.getCounter());
    SmartDashboard.putNumber("WoF Rotations", contestant.getRotations());
    SmartDashboard.putString("TOF 1 Edge", timeofflight.getEdge1());
    SmartDashboard.putString("TOF 2 Edge", timeofflight.getEdge2());
    SmartDashboard.putString("TOF 3 Edge", timeofflight.getEdge3());
    SmartDashboard.putString("Color Match", contestant.getColorinShuffleboard());
    SmartDashboard.putString("Intake Motor State", timeofflight.moveMotor());
    SmartDashboard.putBoolean("Rotation Control", contestant.rotationControl(4));
    SmartDashboard.putBoolean("Position Control", contestant.positionControl());
  }
  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java Smart
   * Dashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
