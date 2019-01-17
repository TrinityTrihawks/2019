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
      return ultrasonicLeft.getVoltage() / (0.0048828) / 5;
  }

  public double getRightDistance() {
      return ultrasonicRight.getVoltage() / (0.0048828) / 5;
  }

}
