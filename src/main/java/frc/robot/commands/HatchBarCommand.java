// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.commands;

// import edu.wpi.first.wpilibj.Joystick.AxisType;
// import edu.wpi.first.wpilibj.command.Command;
// import frc.robot.Robot;
// import frc.robot.RobotMap;

// /**
//  * An example command.  You can replace me with your own command.
// //  *
// public class HatchBarCommand extends Command {

//   public HatchBarCommand() {
//     // Use requires() here to declare subsystem dependencies
//     requires(Robot.hatchBar);

//   }

//   // Called just before this Command runs the first time
//   @Override
//   protected void initialize() {
//   }

//   // Called repeatedly when this Command is scheduled to run
//   @Override
//   protected void execute() {
//     double power = Robot.oi.XboxController.getRawAxis(RobotMap.XboxLeftAxis);
//     power = power * 1;
//     Robot.hatchBar.Lift(power);
//     // System.out.println("Hatch lift: " + power);

//     //TODO: put safeguards in pneumatics extend and retract code
//     if(Robot.oi.XboxController.getRawButton(10)) {
//         Robot.hatchBar.pneumaticsExtend();
//     } else if(Robot.oi.XboxController.getRawButton(9)) {
//         Robot.hatchBar.pneumaticsRetract();
//     }

//     // if(Robot.oi.XboxController.getRawButton(RobotMap.XboxLeftBumper)) {
//     //     Robot.hatchBar.pneumaticsExtend();
//     // } else {
//     //     Robot.hatchBar.pneumaticsRetract();
//     // }

//     if(Robot.oi.XboxController.getRawButton(RobotMap.XboxLeftTrigger)) {
//         Robot.hatchBar.suctionOff();
//     } else if(Robot.oi.XboxController.getRawButton(RobotMap.XboxLeftBumper)) {
//         Robot.hatchBar.suctionIn();
//     } else if(Robot.oi.XboxController.getRawButton(RobotMap.XboxRightBumper)) {
//         Robot.hatchBar.suctionOut();
//     }



//   }

//   // Make this return true when this Command no longer needs to run execute()
//   @Override
//   protected boolean isFinished() {
//       return false;
//   }

//   // Called once after isFinished returns true
//   @Override
//   protected void end() {
//   }

//   // Called when another command which requires one or more of the same
//   // subsystems is scheduled to run
//   @Override
//   protected void interrupted() {
//       end();
//   }
// }
