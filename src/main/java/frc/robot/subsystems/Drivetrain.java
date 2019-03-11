/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

// import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


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

	// Encoder encoderLeft;
	// Encoder encoderRight;

	public Drivetrain(TalonSRX frontLeft, TalonSRX frontRight, TalonSRX backLeft, TalonSRX backRight){

		// create the wheels

		masterLeft = frontLeft;
		masterRight = frontRight;
		slaveLeft = backLeft;
		slaveRight = backRight;

		//reset all Talon config settings to avoid accidental settings carry-over
		masterLeft.configFactoryDefault();
		masterRight.configFactoryDefault();
		slaveLeft.configFactoryDefault();
		slaveRight.configFactoryDefault();

		disableLimitSwitch();
		// set the back wheels to mirror the front wheels
		// slaveLeft.set(ControlMode.Follower, RobotMap.frontLeftWheel);
		// slaveRight.set(ControlMode.Follower, RobotMap.frontRightWheel);

		masterRight.setInverted(true);
		slaveRight.setInverted(true);
		// masterLeft.setInverted(true);
		// slaveLeft.setInverted(true);

		//create the encoders
		// encoderLeft = new Encoder(RobotMap.leftEncoderSourceA, RobotMap.leftEncoderSourceB);
		// encoderRight = new Encoder(RobotMap.rightEncoderSourceA, RobotMap.rightEncoderSourceB);
	}

	public void enableLimitSwitch() {
		masterRight.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
		masterRight.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);

		masterLeft.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
		masterLeft.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
	}
	
	public void disableLimitSwitch() {
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
		// double leftTicks = encoderLeft.get();
		// return leftTicks;
		//  return encoderTicksToDistance(leftTicks);
		return 0;
	}

	public double getRightDistance() {
		// double rightTicks = masterRight.getSensorCollection().getQuadraturePosition();
		// double rightTicks = encoderRight.get();
		// return rightTicks;
		//  return encoderTicksToDistance(rightTicks);
		return 0;
	}

	public void resetDistance() {
		// encoderLeft.reset();
		// encoderRight.reset();
	}

	public double encoderTicksToDistance(double encoderTicks) {
		// return encoderTicks / 360   * RobotMap.wheelCircumference;
		return 0;
	}

	@Override
	public void initDefaultCommand() {
		// setDefaultCommand();
	}
}
