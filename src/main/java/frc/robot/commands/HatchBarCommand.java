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

	// private final double powerScalarTowardsBody = 0.3;
	// private final double powerScalarAwayFromBody = 0.3;

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
		// System.out.println("Hatch lift from command: " + power);

		if(oi.XboxController.getRawButton(RobotMap.XboxRightBumper)) {
			hatchBar.suctionOff();
		} else if(oi.XboxController.getRawButton(RobotMap.XboxLeftBumper)) {
			hatchBar.suctionIn();
		}
		// } else if(oi.XboxController.getRawButton(RobotMap.XboxRightBumper)) {
		// 	hatchBar.suctionOut();
		// }

		if(oi.XboxController.getRawButtonPressed(RobotMap.XboxLeftTrigger)) {
			hatchBar.releaseAir();
		} else if(oi.XboxController.getRawButtonPressed(RobotMap.XboxRightTrigger)) {
			hatchBar.keepAir();
		}

		if(oi.XboxController.getRawButton(RobotMap.XboxRightTrigger)) {
			hatchBar.overrideArmLimits();
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
