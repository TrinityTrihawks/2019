package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.profiling.BlindProfileUtil;
import frc.robot.profiling.TrajectoryPoint;
import frc.robot.subsystems.Drivetrain;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveBlindProfile extends Command {

    private final Drivetrain drivetrain;
    private final TrajectoryPoint[] trajectory;

    private final double maxVel = 9; // ft/sec
	private final double maxAccel = 10; // ft/sec^2
	private final double voltageMax = 12; // volts
	private final double voltageDead = 1; // deadband where robot does not accel (volts)
	
    private final double Kv = (voltageMax - voltageDead) / maxVel;
	private final double Ka = (voltageMax - voltageDead) / maxAccel;
	
    private int counter;

	public DriveBlindProfile(Drivetrain drivetrain, double distance) {
        this.drivetrain = drivetrain;

        trajectory = BlindProfileUtil.generateStraightLineTrajectory(distance, maxVel, maxAccel);

		// Use requires() here to declare subsystem dependencies
		requires(this.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
        drivetrain.Drive(0, 0);
        counter = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
        double vel = trajectory[counter].vel;
		double accel = trajectory[counter].accel;

		double motorVoltage = Kv * vel + voltageDead;

		System.out.printf("\nTime: %f Vel: %f Accel: %f Voltage: %f",
			trajectory[counter].time, vel, accel, motorVoltage
		);

		double modifiedMV = motorVoltage * 1;
		drivetrain.Drive(modifiedMV / 12, modifiedMV / 12);
		
		counter += 1;
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if(counter >= trajectory.length) {
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		drivetrain.Drive(0,0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
