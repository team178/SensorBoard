/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.subsystems.Lights;
import frc.robot.commands.*;
//import frc.robot.commands.GetTofDistance;
//import frc.robot.commands.MoveMotor;

/**
 * Add your docs here.
 */
public class OI {
    public static Joystick xbox = new Joystick(RobotMap.xbox);
    public static Button a = new JoystickButton(xbox, 1);
    public static Button b = new JoystickButton(xbox, 2);
    public static Button x = new JoystickButton(xbox, 3);
    public static Button y = new JoystickButton(xbox, 4);
    
    public OI () {
       // xboxA.whenPressed(new MoveMotor());
      //xboxA.whenPressed(new GetTofDistance());
    }
}
