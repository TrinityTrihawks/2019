package frc.robot.profiling;

public class TrajectoryPoint {
    public double time;
    public double vel;
    public double accel;

    public TrajectoryPoint(double time, double vel, double accel) {
        this.time = time;
        this.vel = vel;
        this.accel = accel;
    }
}