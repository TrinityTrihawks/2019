package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.logging.Visualizer;
import frc.robot.subsystems.Drivetrain;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveAtVoltage extends Command {

    private final Drivetrain drivetrain;
	
	private int counter;
	
	private double[] desiredVoltageLeft;
	private double[] desiredVoltageRight;
	private double[] actualVoltageLeft;
	private double[] actualVoltageRight;

	private double leftScalar;
	private double rightScalar;

	private double targetVoltage;
	private double duration;

	public DriveAtVoltage(Drivetrain drivetrain, double voltage, double duration) {
		this.drivetrain = drivetrain;

		this.targetVoltage = voltage;
		this.duration = duration;

		leftScalar = SmartDashboard.getNumber("Left Scalar", RobotMap.leftScalar);
		rightScalar = SmartDashboard.getNumber("Right Scalar", RobotMap.rightScalar);

		int durationInFrames = (int) Math.ceil(duration * 50);

		actualVoltageLeft = new double[durationInFrames];
		actualVoltageRight = new double[durationInFrames];
		desiredVoltageLeft = new double[durationInFrames];
		desiredVoltageRight = new double[durationInFrames];

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

		double motorVoltage_left = targetVoltage * leftScalar;
		double motorVoltage_right = targetVoltage * rightScalar;

		double modifiedMV_left = motorVoltage_left;
		double modifiedMV_right = motorVoltage_right;
		drivetrain.Drive(modifiedMV_left / 12, modifiedMV_right / 12);

		actualVoltageLeft[counter] = drivetrain.getFrontLeftVoltage();
		actualVoltageRight[counter] = drivetrain.getFrontRightVoltage();
		desiredVoltageLeft[counter] = motorVoltage_left;
		desiredVoltageRight[counter] = motorVoltage_right;
		
		counter += 1;
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if(counter >= duration * 50) {
			return true;
		} else {
			return false;
		}
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		drivetrain.Drive(0,0);

		double[] time = new double[desiredVoltageLeft.length];
		for(int i=0; i < time.length; i++) {
			time[i] = i * 0.02;
		}

		Visualizer visualizer = new Visualizer();
		visualizer.clear();

		visualizer.setXAxis("Time", time);
		visualizer.push("Desired Left Voltage", desiredVoltageLeft);
		visualizer.push("Desired Right Voltage", desiredVoltageRight);
		visualizer.push("Actual Left Voltage", actualVoltageLeft);
		visualizer.push("Actual Right Voltage", actualVoltageRight);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
