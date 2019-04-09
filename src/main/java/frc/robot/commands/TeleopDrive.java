/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.GlobalState;
import frc.robot.OI;
import frc.robot.GlobalState.DrivePerspectives;
import frc.robot.subsystems.Drivetrain;


/**
 * An example command.  You can replace me with your own command.
 */
public class TeleopDrive extends Command {

  final Drivetrain drivetrain;
  final OI oi;
  final GlobalState state;

  public TeleopDrive(Drivetrain drivetrain, OI oi, GlobalState state) {
    this.drivetrain = drivetrain;
    this.oi = oi;
    this.state = state;

    // Use requires() here to declare subsystem dependencies
    requires(this.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // get power values from the controller
    double magnitude = oi.getJoystickVerticalAxis();
    double twist = oi.getJoystickTwist();
    double slider = oi.getSlider();
    boolean shouldDriveStraight = oi.getJoystickSideButton();
    // boolean driveInReverse = Robot.oi.controller.getRawButton(RobotMap.driveInReverseButton); 
    //System.out.println("Drive straight: "+shouldDriveStraight);

    if(slider < 0.1) {
      slider = 0.1;
    }

    if(Math.abs(magnitude) < 0.2) {
      magnitude = 0;
    }

    if(shouldDriveStraight == true) {
      twist = 0;
    }

    if(Math.abs(twist) < .2) {
      twist = 0;
    }

 
    //magnitude *= .5;
    twist *= .4;
    
    // if(state.getPerspective() == DrivePerspectives.CARGO) {
    //   magnitude = -1 * magnitude;
    // }

    double leftPower = magnitude * slider + twist;
    double rightPower = magnitude * slider - twist;

    // System.out.println("leftPower: " + leftPower +" rightPower: " + rightPower + " twist: " + twist);

    drivetrain.Drive(leftPower, rightPower);

    // double leftEncoder = drivetrain.getLeftDistance();
    // double rightEncoder = drivetrain.getRightDistance();
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
