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
public class AutonomousDriveForward extends Command {

  double targetDistance; 

  public AutonomousDriveForward(double targetDistance) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);

    this.targetDistance = targetDistance;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivetrain.Drive(0.5, 0.5);
    System.out.println("Drive forward command starting");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double leftEncoder = Robot.drivetrain.getLeftDistance();
    double rightEncoder = Robot.drivetrain.getRightDistance();
    System.out.println("Left Encoder: "+ leftEncoder);
    System.out.println("Right encoder: "+ rightEncoder);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    double leftDistance = Robot.drivetrain.getLeftDistance();
    // double rightDistance = Robot.drivetrain.getRightDistance();

    if(leftDistance >= targetDistance) {
        return true;
    } else {
        return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
      Robot.drivetrain.Drive(0, 0);
      System.out.println("Drive forward ended");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
      end();
  }
}
