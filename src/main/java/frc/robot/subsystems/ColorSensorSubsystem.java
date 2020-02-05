package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import com.revrobotics.ColorMatch;

import org.letsbuildrockets.libs.ColorSensor;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;



public class ColorSensorSubsystem extends Subsystem {

  ColorSensor colorsensor;
  double rot;
  String initColor;
  String currentColor;

  public static final Color Blue = ColorMatch.makeColor(0.153, 0.445, 0.402);
  public static final Color Green = ColorMatch.makeColor(0.196, 0.557, 0.246);
  public static final Color Red = ColorMatch.makeColor(0.475, 0.371, 0.153);
  public static final Color Yellow = ColorMatch.makeColor(0.319, 0.545, 0.136);
  public static final Color Black = ColorMatch.makeColor(0,0,0);

  public ColorSensorSubsystem() {
    colorsensor = new ColorSensor();
    rot = 0;
    initColor = getColor();
    currentColor = getColor();
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
    if ((a.red < b.red + 0.02) && (a.red > b.red - 0.02)) {
      if ((a.green < b.green + 0.02) && (a.green > b.green - 0.02)) {
        if ((a.blue < b.blue + 0.02) && (a.blue > b.blue - 0.02)) {
          return true;
        }
      } 
    }
    return false;
  }

  public double getRotations() {
    if (initColor == "No Color") {
      initColor = getColor();
      return 0;
    }
    if (currentColor != getColor()) {
      currentColor = getColor();
      rot+=0.125;
      return rot;
    }
    return rot;
  }

  public void initDefaultCommand () {

  }
}
