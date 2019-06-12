package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.logging.Visualizer;
import frc.robot.profiling.BlindProfileUtil;
import frc.robot.profiling.TrajectoryPoint;
import frc.robot.subsystems.Drivetrain;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveBlindProfile extends Command {

    private final Drivetrain drivetrain;
	private final TrajectoryPoint[] trajectory;

	private final double voltageDead;
	private final double Kv_left;
    private final double Kv_right;
	private final double Ka_left;
	private final double Ka_right;
	
	private int counter;
	
	private double[] desiredVoltageLeft;
	private double[] desiredVoltageRight;
	private double[] actualVoltageLeft;
	private double[] actualVoltageRight;

	public DriveBlindProfile(Drivetrain drivetrain, double distance) {
		this.drivetrain = drivetrain;

		double maxVel = SmartDashboard.getNumber("Max Velocity", RobotMap.maxVel);
		double maxAccel = SmartDashboard.getNumber("Max Acceleration", RobotMap.maxAccel);
		double voltageMax = SmartDashboard.getNumber("Max Voltage", RobotMap.voltageMax);
		double leftScalar = SmartDashboard.getNumber("Left Scalar", RobotMap.leftScalar);
		double rightScalar = SmartDashboard.getNumber("Right Scalar", RobotMap.rightScalar);
		voltageDead = SmartDashboard.getNumber("Deadband Voltage", RobotMap.voltageDead);

			
		Kv_left = (voltageMax * leftScalar - voltageDead) / maxVel;
		Kv_right = (voltageMax * rightScalar - voltageDead) / maxVel;
		Ka_left = voltageMax * leftScalar / maxVel;
		Ka_right = voltageMax * rightScalar / maxVel;

		trajectory = BlindProfileUtil.generateStraightLineTrajectory(distance, maxVel, maxAccel);
		
		actualVoltageLeft = new double[trajectory.length];
		actualVoltageRight = new double[trajectory.length];
		desiredVoltageLeft = new double[trajectory.length];
		desiredVoltageRight = new double[trajectory.length];

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

		double motorVoltage_left = Kv_left * vel + Ka_left * accel * 0.02 + voltageDead;
		double motorVoltage_right = Kv_right * vel + Ka_right * accel * 0.02 + voltageDead;

		double modifiedMV_left = motorVoltage_left * 1;
		double modifiedMV_right = motorVoltage_right * 1;
		drivetrain.Drive(modifiedMV_left / 12, modifiedMV_right / 12);

		actualVoltageLeft[counter] = drivetrain.getFrontLeftVoltage();
		actualVoltageRight[counter] = drivetrain.getFrontRightVoltage();
		desiredVoltageLeft[counter] = motorVoltage_left;
		desiredVoltageRight[counter] = motorVoltage_right;

		// if(counter % 10 == 0) {
		// 	double actualVoltage_left = drivetrain.getFrontLeftVoltage();
		// 	double actualVoltage_right = drivetrain.getFrontRightVoltage();
	
		// 	System.out.printf("\nTime: %f Vel: %f Accel: %f Voltage left: %f Actual: %f Voltage right: %f Actual: %f",
		// 		trajectory[counter].time, vel, accel, motorVoltage_left, motorVoltage_right
		// 	);
		// }

		
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

		double time[] = new double[trajectory.length];
		double pos[] = new double[trajectory.length];
		double vel[] = new double[trajectory.length];
		double accel[] = new double[trajectory.length];

		for(int i=0; i< trajectory.length; i++) {
			time[i] = trajectory[i].time;
			pos[i] = trajectory[i].pos;
			vel[i] = trajectory[i].vel;
			accel[i] = trajectory[i].accel;
		}

		Visualizer visualizer = new Visualizer();
		visualizer.clear();

		visualizer.setXAxis("Time", time);
		visualizer.push("Desired Position", pos);
		visualizer.push("Desired Velocity", vel);
		visualizer.push("Desired Acceleration", accel);
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
