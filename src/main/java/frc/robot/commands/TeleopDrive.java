/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Robot.DrivePerspectives;

/**
 * An example command.  You can replace me with your own command.
 */
public class TeleopDrive extends Command {

  public TeleopDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);
    // requires(Robot.pneumatics);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // get power values from the controller
    double magnitude = Robot.oi.getJoystickVerticalAxis();
    double twist = Robot.oi.getJoystickTwist();
    double slider = Robot.oi.getSlider();
    boolean shouldDriveStraight = Robot.oi.getJoystickSideButton();
    // boolean driveInReverse = Robot.oi.controller.getRawButton(RobotMap.driveInReverseButton); 
    //System.out.println("Drive straight: "+shouldDriveStraight);

    if(slider < 0.1) {
      slider = 0.1;
    }

    if(Math.abs(magnitude) < 0.4) {
      magnitude = 0;
    }

    if(shouldDriveStraight == true) {
      twist = 0;
    }

 
    //magnitude *= .5;
    twist *= .5;

    
    if(Robot.getDrivePerspective() == DrivePerspectives.CARGO) {
      magnitude = -1 * magnitude;
    }

    double leftPower = magnitude * slider + twist;
    double rightPower = magnitude * slider - twist;

    System.out.println("leftPower: " + leftPower +" rightPower: " + rightPower + " twist: " + twist);

    Robot.drivetrain.Drive(leftPower, rightPower);

    double leftEncoder = Robot.drivetrain.getLeftDistance();
    double rightEncoder = Robot.drivetrain.getRightDistance();
    // System.out.println("Left Encoder: "+ leftEncoder);
    // System.out.println("Right encoder: "+ rightEncoder);


    // boolean trigger = Robot.oi.getJoystickTrigger();
    // if (trigger)
    // {
    //   Robot.drivetrain.DisableLimitSwitch();
    // }
    // else
    // {
    //   Robot.drivetrain.EnableLimitSwitch();
    // }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
      end();
  }

  private double[] scalePowerToValidInterval(double leftPower, double rightPower) {
      double[] newPower = {0, 0}; //left power and right power (in that order)
      // scale power to valid range and keep the ratio between the left and right powers equal
      if(Math.abs(leftPower) > 1) {
        newPower[0] = leftPower / Math.abs(leftPower);
        newPower[1] = rightPower / Math.abs(leftPower);
      } else if(Math.abs(rightPower) > 1) {
        newPower[0] = rightPower / Math.abs(rightPower);
        newPower[1] = leftPower / Math.abs(rightPower);
      } else {
        newPower[0] = leftPower;
        newPower[1] = rightPower;
      }
      
      return newPower;
  }
}
