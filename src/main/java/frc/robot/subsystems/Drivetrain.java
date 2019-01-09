/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.TeleopDrive;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.motorcontrol.*;



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

  public Drivetrain(){
    // create the wheels
    masterLeft = new TalonSRX(RobotMap.frontLeftWheel);
    masterRight = new TalonSRX(RobotMap.frontRightWheel);
    slaveLeft = new TalonSRX(RobotMap.backLeftWheel);
    slaveRight = new TalonSRX(RobotMap.backRightWheel);

    // set the back wheels to mirror the front wheels
    slaveLeft.set(ControlMode.Follower, RobotMap.frontLeftWheel);
    slaveRight.set(ControlMode.Follower, RobotMap.frontRightWheel);

    // create the gyro
    gyro = new PigeonIMU(RobotMap.gyro);
  }
  

  public void Drive(double leftPower, double rightPower){
    // Set power to left and right sides of the robot
    masterLeft.set(ControlMode.PercentOutput, leftPower);
    masterRight.set(ControlMode.PercentOutput, rightPower);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TeleopDrive());
  }
}
