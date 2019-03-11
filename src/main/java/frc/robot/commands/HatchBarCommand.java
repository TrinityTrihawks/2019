/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.subsystems.HatchBar;

/**
 * An example command.  You can replace me with your own command.
 */
public class HatchBarCommand extends Command {

	private final HatchBar hatchBar;
	private final OI oi;

	public HatchBarCommand(HatchBar hatchBar, OI oi) {
		this.hatchBar = hatchBar;
		this.oi = oi;

		// Use requires() here to declare subsystem dependencies
		requires(this.hatchBar);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double power = oi.XboxController.getRawAxis(RobotMap.XboxLeftAxis);

		// System.out.println("angle: " + Robot.hatchBar.getArmAngle());

		// double baseLinePower = 0;
		// if(Robot.hatchBar.getArmAngle() < 90) {
		//     baseLinePower = 0.2;
		// }

		hatchBar.Lift(power);
		// System.out.println("Hatch lift: " + power);

		if(oi.XboxController.getRawButton(RobotMap.XboxLeftTrigger)) {
			hatchBar.suctionOff();
		} else if(oi.XboxController.getRawButton(RobotMap.XboxLeftBumper)) {
			hatchBar.suctionIn();
		} else if(oi.XboxController.getRawButton(RobotMap.XboxRightBumper)) {
			hatchBar.suctionOut();
		}

		// //TODO: put safeguards in pneumatics extend and retract code
		// if(Robot.oi.XboxController.getRawButton(10)) {
		//     Robot.hatchBar.pneumaticsExtend();s
		// } else if(Robot.oi.XboxController.getRawButton(9)) {
		//     Robot.hatchBar.pneumaticsRetract();
		// }

		// if(Robot.oi.XboxController.getRawButton(RobotMap.XboxLeftBumper)) {
		//     Robot.hatchBar.pneumaticsExtend();
		// } else {
		//     Robot.hatchBar.pneumaticsRetract();
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
}
