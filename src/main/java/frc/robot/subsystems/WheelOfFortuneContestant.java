package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorMatch;

import org.letsbuildrockets.libs.ColorSensor;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.RobotMap;



public class WheelOfFortuneContestant extends Subsystem {

  VictorSPX motor;
  ColorSensor colorsensor;
  double rot;
  char randomColor;
  char initColor;
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
    randomColor = getRandomColor();
    initColor = getColor();
    countTrigger = false;
  }

  public char getGameData() {
    if(gameData.length() > 0) {
      if(gameData.charAt(0) == 'B') {
        return 'B';
      }

      if(gameData.charAt(0) == 'G') {
        return 'G';
      }

      if(gameData.charAt(0) == 'R') {
        return 'R';
      }

      if(gameData.charAt(0) == 'Y') {
        return 'Y';
      }
    }
    return 'N';
  }

  public char getColor() {
    Color c = colorsensor.detectColor();
    SmartDashboard.putNumber("Red", c.red);
    SmartDashboard.putNumber("Green", c.green);
    SmartDashboard.putNumber("Blue", c.blue);
    if (compareColors(c, Blue))
      return 'B';
    if (compareColors(c, Green))
      return 'G';
    if (compareColors(c, Red))
      return 'R';
    if (compareColors(c, Yellow))
      return 'Y';
    return 'N';
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
    if (initColor == 'N') {
      initColor = getColor(); //This might not work, only becuase the initColor would need to be a consistant one color.
      return 0;               //The rot value counts the times that it sees one color and creates the rot value
    } else {
      countTrigger = initColor != getColor();
    } 
      if(getColor() == initColor && countTrigger) {
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
    if (randomColor == getColor()) { //getGameData() used when finished testing
      motor.set(ControlMode.PercentOutput, 0.5);
      return true;
    }
    motor.set(ControlMode.PercentOutput, 0);
    return false;
  }

  public char getRandomColor() {
    char[] colors = {'B', 'R', 'G', 'Y'};  
    return colors[(int) (Math.random() * 4)]; //random number from 0-3
  }

  public void initDefaultCommand() { //not used yet - needs to be here tho
  }

  public String getColorinShuffleboard() { //method for shuffleboard printing a string as it will not work for type char
    Color c = colorsensor.detectColor();
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
}
