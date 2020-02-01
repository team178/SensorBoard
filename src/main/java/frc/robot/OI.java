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
//import frc.robot.commands.GetTofDistance;
import frc.robot.commands.MoveMotor;

/**
 * Add your docs here.
 */
public class OI {
    public static Joystick xbox = new Joystick(RobotMap.xbox);
    public Button xboxA = new JoystickButton(xbox, 1);
    
    public OI () {
        xboxA.whenPressed(new MoveMotor());
      //xboxA.whenPressed(new GetTofDistance());
    }
}
