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
public class HatchBarSteadyPower extends Command {

	private final HatchBar hatchBar;
	private final OI oi;
	double power;

	public HatchBarSteadyPower(HatchBar hatchBar, OI oi, double power) {
		this.hatchBar = hatchBar;
		this.oi = oi;
		// Use requires() here to declare subsystem dependencies
		requires(this.hatchBar);

		this.power = power;

	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		hatchBar.Lift(power);
		System.out.println("power "+ power);

		// if(Robot.oi.XboxController.getRawButton(RobotMap.XboxLeftBumper)) {
		//     Robot.hatchBar.pneumaticsExtend();
		// } else {
		//     Robot.hatchBar.pneumaticsRetract();
		// }

		if(oi.XboxController.getRawButton(RobotMap.XboxLeftTrigger)) {
			hatchBar.suctionOff();
		} else if(oi.XboxController.getRawButton(RobotMap.XboxLeftBumper)) {
			hatchBar.suctionIn();
		} else if(oi.XboxController.getRawButton(RobotMap.XboxRightBumper)) {
			hatchBar.suctionOut();
		}
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
