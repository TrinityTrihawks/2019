/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.TeleopDrive;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  TalonSRX masterLeft;
  TalonSRX masterRight;
  TalonSRX slaveLeft;
  TalonSRX slaveRight;

  PigeonIMU gyro;
  Encoder encoderLeft;
  Encoder encoderRight;

  public Drivetrain(){
    // create the wheels
    masterLeft = new TalonSRX(RobotMap.frontLeftWheel);
    masterRight = new TalonSRX(RobotMap.frontRightWheel);
    slaveLeft = new TalonSRX(RobotMap.backLeftWheel);
    slaveRight = new TalonSRX(RobotMap.backRightWheel);

    // masterRight.configSelectedFeedbackSensor(FeedbackDevice.Tachometer);
    masterRight.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled);
    masterRight.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled);

    masterLeft.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled);
    masterLeft.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled);

    // set the back wheels to mirror the front wheels
    // slaveLeft.set(ControlMode.Follower, RobotMap.frontLeftWheel);
    // slaveRight.set(ControlMode.Follower, RobotMap.frontRightWheel);

    masterRight.setInverted(true);
    slaveRight.setInverted(true);
    // masterLeft.setInverted(true);
    // slaveLeft.setInverted(true);

    // create the gyro
    gyro = new PigeonIMU(RobotMap.gyro);

    //create the encoders
    encoderLeft = new Encoder(RobotMap.leftEncoderSourceA, RobotMap.leftEncoderSourceB);
    encoderRight = new Encoder(RobotMap.rightEncoderSourceA, RobotMap.rightEncoderSourceB);

    // masterRight.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
    // masterRight.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);

    // slaveRight.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);

  }

  public void EnableLimitSwitch()
  {
    masterRight.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
    masterRight.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);

    masterLeft.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
    masterLeft.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
  }
  
  public void DisableLimitSwitch()
  {
    masterRight.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled);
    masterRight.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled);

    masterLeft.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled);
    masterLeft.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled);
  }

  public void Drive(double leftPower, double rightPower){
    // Set power to left and right sides of the robot
    masterLeft.set(ControlMode.PercentOutput, leftPower);
    slaveLeft.set(ControlMode.PercentOutput, leftPower);

    masterRight.set(ControlMode.PercentOutput, rightPower);
    slaveRight.set(ControlMode.PercentOutput, rightPower);

    // System.out.println("Left power: "+leftPower+ " Right power: "+ rightPower);

    // boolean fwdClosed = masterRight.getSensorCollection().isFwdLimitSwitchClosed();
    // boolean revClosed = masterRight.getSensorCollection().isRevLimitSwitchClosed();
    // System.out.println("Fwd closed: "+ fwdClosed);
    // System.out.println("Rev closed: "+ revClosed);


  }

  public double getLeftDistance() {
    // double leftTicks = masterLeft.getSensorCollection().getQuadraturePosition();
    double leftTicks = encoderLeft.get();
    // return leftTicks;
     return encoderTicksToDistance(leftTicks);
  }

  public double getRightDistance() {
    // double rightTicks = masterRight.getSensorCollection().getQuadraturePosition();
    double rightTicks = encoderRight.get();
    // return rightTicks;
     return encoderTicksToDistance(rightTicks);
  }

  public void resetDistance() {
    encoderLeft.reset();
    encoderRight.reset();
  }

  public double encoderTicksToDistance(double encoderTicks) {
    return encoderTicks / 360   * RobotMap.wheelCircumference;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TeleopDrive());
  }
}
