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
  // public static   int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  public static final int controller = 0 ; // id of controller with respect to computer
  public static final int verticalAxis = 2; // id of vertical axis within controller

  public static final int XboxController = 1;
  
  public static final int XboxLeftAxis = 1;
  public static final int XboxRightAxis = 3;
  public static final int XboxLeftBumper = 5;
  public static final int XboxLeftTrigger = 7;
  public static final int XboxRightBumper = 6;
  public static final int XboxRightTrigger = 8;
  public static final int XboxButtonX = 1;
  public static final int XboxButtonA = 2;
  public static final int XboxButtonB = 3;
  public static final int XboxButtonY = 4;

  //TODO: confirm these IP addresses on the 2019 competition robot
  public static final String cameraFrontIPAddress = "10.42.15.36";
  public static final String cameraBackIPAddress = "10.42.15.37";

  // port numbers of TalonSRXs on the RoboRio
  public static final int frontLeftWheel = 1;
  public static final int frontRightWheel = 9;
  public static final int backLeftWheel = 3;
  public static final int backRightWheel = 2;

  // Hatch Bar
  public static final int hatchBarTalonSRX = 6;
  public static final int hatchBarVictorSPX = 4;
  public static final int vacuumMotor1 = 0;
  public static final int vacuumMotor2 = 1;

  //Cargo arm
  public static final int cargoIntake = 0;
  public static final int cargoLift = 10;

  // port number of PigeonIMU gyro on the RoboRio
  public static final int gyro = 1;

  // Ultrasonic PWM ports on the RoboRio
  public static final int leftUltrasonic = 0;
  public static final int rightUltrasonic = 1;

  // Encoder DIO ports in the RoboRio, two for each encoder
  public static final int leftEncoderSourceA = 1;
  public static final int leftEncoderSourceB = 0;
  public static final int rightEncoderSourceA = 2;
  public static final int rightEncoderSourceB = 3;

  //Pneumatics ports in the Pneumatics Control Module (PCM)
  public static final int solenoidForwardChannel = 1;
  public static final int solenoidReverseChannel = 0;

  //wheel circumference in inches
  public static final double wheelCircumference = 18.84955592153876;
  
}
