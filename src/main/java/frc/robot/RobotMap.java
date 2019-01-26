/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  public static final int controller = 0 ; // id of controller with respect to computer
  public static final int verticalAxis = 1; // id of vertical axis within controller

  public static final String cameraIPAddress = "10.42.15.39";

  // port numbers of TalonSRXs on the RoboRio
  // TODO: use correct port numbers 2019 robot wheels
  public static final int frontLeftWheel = 1;
  public static final int frontRightWheel = 0;
  public static final int backLeftWheel = 3;
  public static final int backRightWheel = 2;

  // port number of PigeonIMU gyro on the RoboRio
  public static final int gyro = 1;

  // Ultrasonic port numbers
  public static final int leftUltrasonic = 0;
  public static final int rightUltrasonic = 1;
  //pneumatics
  public static final int solenoidForwardChannel = 1;
  public static final int solenoidReverseChannel = 0;

  //TODO: measure wheel circumference
  public static final double wheelCircumference = 0;

}
