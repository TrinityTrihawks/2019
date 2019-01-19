package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class Ultrasonics {


  AnalogInput ultrasonicLeft;
  AnalogInput ultrasonicRight;

  public Ultrasonics() {
    ultrasonicLeft = new AnalogInput(RobotMap.leftUltrasonic);
    ultrasonicRight = new AnalogInput(RobotMap.rightUltrasonic);
  }

  public double getLeftDistance() {
    double leftVoltage = ultrasonicLeft.getVoltage();
    return scaleVoltageValue(leftVoltage);
  }

  public double getRightDistance() {
    double rightVoltage = ultrasonicRight.getVoltage();
    return scaleVoltageValue(rightVoltage);
  }

  private double scaleVoltageValue(double voltageValue) {
    return voltageValue / (0.0048828) / 5;
  }

}
