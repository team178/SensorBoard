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
/**
 * @author Evan Czerwinski
 * @author Varun Chamarty
 * @author Robbie Fishel
 * @version 4.0
 * @since 2020.1.2
 * 
 */



public class TestWheelOfFortuneContestant extends Subsystem {

  VictorSPX motor; //varible for motor to spin control panel
  ColorSensor colorsensor; //variable for color sensor to sense the colors on control panel
  double rot; //variable for rotation tracking variable
  char randomColor; //variable for random color - not used on final bot
  char initColor; //variable to get the current color
  boolean countTrigger; //variable to detect WOF rotations - works in part with rot variable


  /*
  * Creates colors that we tested in order to determine what color we are sensing
  */
  public static final Color TestBlue = ColorMatch.makeColor(0.208, 0.471, 0.320);
  public static final Color TestGreen = ColorMatch.makeColor(0.240, 0.568, 0.191);
  public static final Color TestRed = ColorMatch.makeColor(0.504, 0.353, 0.142);
  public static final Color TestYellow = ColorMatch.makeColor(0.312, 0.546, 0.140);
  public static final Color TestBlack = ColorMatch.makeColor(0,0,0);
  private String gameData = DriverStation.getInstance().getGameSpecificMessage();

  /*
  * Initializes variables from above to their appropiate values
  */
  public TestWheelOfFortuneContestant() {
    motor = new VictorSPX(RobotMap.contestant);
    colorsensor = new ColorSensor();
    rot = 0;
    randomColor = getRandomColor();
    initColor = getColor();
    countTrigger = false;
  }

  /** 
   * @return 'B' is the string that we get from the game
   * Method used to get the game data
   * 
  */

  public char getGameData() {
    if(gameData.length() > 0) {
      if(gameData.charAt(0) == 'B') {
        return 'R';
      }

      if(gameData.charAt(0) == 'G') {
        return 'Y';
      }

      if(gameData.charAt(0) == 'R') {
        return 'B';
      }

      if(gameData.charAt(0) == 'Y') {
        return 'G';
      }
    }
    return 'N';
  }

  
  /** 
   * @return char
   */
  /*
  * Method to get the current sensed color
  */
  public char getColor() {
    Color c = colorsensor.detectColor();
    SmartDashboard.putNumber("TestRed", c.red);
    SmartDashboard.putNumber("TestGreen", c.green);
    SmartDashboard.putNumber("TestBlue", c.blue);
    if (compareColors(c, TestBlue))
      return 'B';
    if (compareColors(c, TestGreen))
      return 'G';
    if (compareColors(c, TestRed))
      return 'R';
    if (compareColors(c, TestYellow))
      return 'Y';
    return 'N';
  }

  /** Desired color in comparison from what we are seeing.
   * @param Color a  takes the color value of what the sensor senses
   * @param Color b  takes the color of the color we sense
   * @return  A boolean variable stating whether what color we want is in a tolerable range to be considered the right color
   * 
  * Method that uses ranges to determine whether the color we are sensing is similar to the colors we tested
  */
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

    /** Gets rotations of the wheel
     * @return rotation values 
    * 
    * Method that returns the number of 
    *  rotations the color wheel has made
    */
  public double getRotations() {
    if (initColor == 'N') {
      initColor = getColor(); //This might not work, only becuase the initColor would need to be a consistant one color.
      return 0;
    }
    
    if (initColor != getColor() && getColor() != 'N') {
      countTrigger = true;
    }
    
    if (countTrigger) {
      if (initColor == getColor()) {
        rot+=0.5;
        countTrigger = false;
      }

      if(rot >= 5)
      {
        rot = 0;
      }
    }
    return rot;
  }

  /**
   * @param int desiredRotations  indicates how many times 
   * control panal should spin
   * @return boolean depending on whether rotation criteria has been met
   * 
   * Method that uses motor control to rotate the color wheel
   * Detects when the wheel has spun enough times in order to stop automatically
   */
  
  public boolean rotationControl(int desiredRotations) {
    if (getRotations() < desiredRotations) {
      return false;
    }
    return true;
  }

  public void spinRC() {
    if (!rotationControl(3)) {
      motor.set(ControlMode.PercentOutput, .5);
    } else {
      motor.set(ControlMode.PercentOutput, 0);
    }
  }

  /** 
   * @return boolean depending on whether game data is equal to detected color
   * Method that uses the game data to determine whether the current 
   * color is the same as the color the robot needs to spin the wheel to
  */
  public boolean positionControl() {
    if (randomColor != getColor()) { //getGameData() used when finished testing
      return false;
    }
    return true;
  }

  public void spinPC() {
    if(!positionControl()) {
      motor.set(ControlMode.PercentOutput, .5);
    } else {
      motor.set(ControlMode.PercentOutput, 0);
    }
  }

  /**
   * @return random color value
   * 
   * Temporary method that chooses a random color
  * Used for testing as if we were receiving a random color from the game data
   */

  public char getRandomColor() {
    char[] colors = {'B', 'R', 'G', 'Y'};  
    return colors[(int) (Math.random() * 4)]; //random number from 0-3
  }

  
  /** 
   * @param getColorinShuffleboard(
   */
  public void initDefaultCommand() { //not used yet - needs to be here though
  }

  /** 
   * @return SmartDashboard Strings that are displayed
   * 
   * Method for shuffleboard to print the current
   *  color as it will not work for type char
  */
  public String testGetColorinShuffleboard() { 
    Color c = colorsensor.detectColor();
    SmartDashboard.putNumber("TestRed", c.red);
    SmartDashboard.putNumber("TestGreen", c.green);
    SmartDashboard.putNumber("TestBlue", c.blue);
    if (compareColors(c, TestBlue))
      return "Blue";
    if (compareColors(c, TestGreen))
      return "Green";
    if (compareColors(c, TestRed))
      return "Red";
    if (compareColors(c, TestYellow))
      return "Yellow";
    return "No Color";
  }
  
    
}


