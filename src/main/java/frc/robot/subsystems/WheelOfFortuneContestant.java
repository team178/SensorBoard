package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorMatch;

import org.letsbuildrockets.libs.ColorSensor;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.RobotMap;



public class WheelOfFortuneContestant extends Subsystem {

  VictorSPX motor;
  ColorSensor colorsensor;
  double rot;
  String initColor;
  boolean countTrigger;

  public static final Color Blue = ColorMatch.makeColor(0.153, 0.445, 0.402);
  public static final Color Green = ColorMatch.makeColor(0.196, 0.557, 0.246);
  public static final Color Red = ColorMatch.makeColor(0.475, 0.371, 0.153);
  public static final Color Yellow = ColorMatch.makeColor(0.319, 0.545, 0.136);
  public static final Color Black = ColorMatch.makeColor(0,0,0);
  private String gameData = DriverStation.getInstance().getGameSpecificMessage();

  public WheelOfFortuneContestant() {
    motor = new VictorSPX(RobotMap.motor1);
    colorsensor = new ColorSensor();
    rot = 0;
    initColor = getColor();
    countTrigger = false;
  }

  public String getGameData() {
    if(gameData.length() > 0) {
      if(gameData.charAt(0) == 'B') {
        return "Blue";
      }

      if(gameData.charAt(0) == 'G') {
        return "Green";
      }

      if(gameData.charAt(0) == 'R') {
        return "Red";
      }

      if(gameData.charAt(0) == 'Y') {
        return "Yellow";
      }
    }
    return "No color";
  }

  public String getColor() {
    Color c = colorsensor.detectColor();
    //System.out.println("Red: " + c.red);
    //System.out.println("Green: " + c.green);
    //System.out.println("Blue: " + c.blue);
    SmartDashboard.putNumber("Red", c.red);
    SmartDashboard.putNumber("Green", c.green);
    SmartDashboard.putNumber("Blue", c.blue);
    if (compareColors(c, Blue))
      return "Blue";
    if (compareColors(c, Green))
      return "Green";
    if (compareColors(c, Red))
      return "Red";
    if (compareColors(c, Yellow))
      return "Yellow";
    return "No Color";
  }

  public boolean compareColors(Color a, Color b) {
    if ((a.red < b.red + 0.045) && (a.red > b.red - 0.045)) {
      if ((a.green < b.green + 0.045) && (a.green > b.green - 0.045)) {
        if ((a.blue < b.blue + 0.045) && (a.blue > b.blue - 0.045)) {
          return true;
        }
      } 
    }
    return false;
  }

  public double getRotations() {
    if (initColor == "No Color") {
      initColor = getColor(); //This might not work, only becuase the initColor would need to be a consistant one color.
      return 0;               //The rot value counts the times that it sees one color and creates the rot value
    } else if (initColor != getColor()) {
      countTrigger = true;
    } else if (getColor() == initColor && countTrigger) {
      rot+=0.5;
      countTrigger = false;
    }
    return rot;
  }

  public boolean rotationControl(int desiredRotations) {
    if (getRotations() < desiredRotations) {
      motor.set(ControlMode.PercentOutput, 1);
      return false;
    }
    motor.set(ControlMode.PercentOutput, 0);
    return true;
  }

  public boolean positionControl() {
    if (getGameData() == getColor()) {
      motor.set(ControlMode.PercentOutput, 0.5);
      return true;
    }
    motor.set(ControlMode.PercentOutput, 0);
    return false;
  }

  public void initDefaultCommand () {

  }
}
