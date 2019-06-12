package frc.robot.profiling;

public class TrajectoryPoint {
    public double time;
    public double pos;
    public double vel;
    public double accel;

    public TrajectoryPoint(double time, double accel, double vel, double pos) {
        this.time = time;
        this.pos = pos;
        this.vel = vel;
        this.accel = accel;
    }
}