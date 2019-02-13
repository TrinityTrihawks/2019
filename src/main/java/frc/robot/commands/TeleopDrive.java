/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class TeleopDrive extends Command {

  public TeleopDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);
    requires(Robot.pneumatics);
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
    System.out.println("Drive straight: "+shouldDriveStraight);

    if(slider < 0.1) {
      slider = 0.1;
    }

    if(shouldDriveStraight == true) {
      twist = 0;
    }


    if(Math.abs(magnitude) < 0.4) {
      magnitude = 0;
    }

    // if(Math.abs(twist) < 0.2) {
    //   twist = 0;
    // }
 
    //magnitude *= .5;
    twist *= .5;

    double leftPower = magnitude * slider + twist;
    double rightPower = magnitude * slider - twist;

    //scale power to valid range and keep the ratio between the left and right powers equal
    // if(Math.abs(leftPower) > 1) {
    //   leftPower = leftPower / Math.abs(leftPower);
    //   rightPower = rightPower / Math.abs(leftPower);
    // } else if(Math.abs(rightPower) > 1) {
    //   rightPower = rightPower / Math.abs(rightPower);
    //   leftPower = leftPower / Math.abs(rightPower);
    // }
    // System.out.println("leftPower: " + leftPower +" rightPower: " + rightPower + " twist: " + twist);

    // Set power of the wheels
    System.out.println("Left power: "+leftPower+ " Right power: "+ rightPower);

    Robot.drivetrain.Drive(leftPower, rightPower);

    if(Robot.oi.getJoystickTrigger()) {
      Robot.pneumatics.off();
      System.out.println("Commanding pneumatics off");
    } else if (Robot.oi.getJoystickTopLeftButton()) {
      Robot.pneumatics.goForward();
      System.out.println("Commanding pneumatics forward");
    } else if(Robot.oi.getJoystickTopRightButton()) {  
      Robot.pneumatics.goBackwards();
      System.out.println("Commanding pneumatics backwards");
    } 

    double leftEncoder = Robot.drivetrain.getLeftDistance();
    double rightEncoder = Robot.drivetrain.getRightDistance();
    System.out.println("Left Encoder: "+ leftEncoder);
    System.out.println("Right encoder: "+ rightEncoder);

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
}
