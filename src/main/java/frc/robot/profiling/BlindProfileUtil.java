package frc.robot.profiling;

public class BlindProfileUtil {
    
    private static double dt = 0.02; // 20 milliseconds

    public static TrajectoryPoint[] generateStraightLineTrajectory(double dist, double maxVel, double maxAccel) {

        double tAccel; //timepoint when accel phase ends
        double tCruise; // timepoint when cruise phase ends
        double tDeaccel; // timepoint when deaccel phase ends

        if(maxVel*maxVel / maxAccel > dist) {
            // robot reaches half distance before it reaches max vel
            tAccel = Math.sqrt(dist/maxAccel);
            tCruise = tAccel; // no cruising
            tDeaccel = 2 * tAccel; 
        } else {
            // robot reaches max vel before it reaches half distance
            tAccel = maxVel / maxAccel;
            tCruise = tAccel + (dist / maxVel - maxVel/maxAccel);
            tDeaccel = tCruise + tAccel;
        }

        int frameNum = (int) Math.floor(tDeaccel / dt);

        TrajectoryPoint[] trajectory = new TrajectoryPoint[frameNum];

        double time;
        double accel;
        double vel = 0;

        for(int i=0; i<frameNum; i++) {
            // 1. Calculate time stamp
            time = (i+1) * dt;

            // 2. Calculate acceleration
            if(time > tCruise) {
                // deaccel phase
                accel = -1 * maxAccel;
            } else if(time > tAccel) {
                // cruise phase
                accel = 0;
            } else {
                // accel phase
                accel = maxAccel;
            }

            // 3. Calculate velocity
            vel += accel * dt;

            // 4. Store this frame's calculations
            trajectory[i] = new TrajectoryPoint(time, vel, accel);
        }

        return trajectory;

    }

    public static void printTrajectory(TrajectoryPoint[] trajectory) {
        double dist = 0;

        for(int i=0; i<trajectory.length; i++) {
            dist += trajectory[i].vel * dt; //integrate for distance

            System.out.printf("\nTime: %f Accel: %f Vel: %f Dist: %f",
                trajectory[i].time, trajectory[i].accel, trajectory[i].vel, dist
            );
        }
    }
}