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
  public static final Color Blue = ColorMatch.makeColor(0.136, 0.412, 0.450);
  public static final Color Green = ColorMatch.makeColor(0.196, 0.557, 0.246);
  public static final Color Red = ColorMatch.makeColor(0.475, 0.371, 0.153);
  public static final Color Yellow = ColorMatch.makeColor(0.293, 0.561, 0.144);
  public static final Color Black = ColorMatch.makeColor(0,0,0);

  public ColorSensorSubsystem() {
    colorsensor = new ColorSensor();
  }

  public String getColor() {
    Color c = colorsensor.detectColor();
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

  public void initDefaultCommand () {

  }
}
