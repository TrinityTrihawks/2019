package frc.robot;

public class GlobalState {

    public enum DrivePerspectives {
        HATCH, CARGO;
    }
    DrivePerspectives drivePerspective;

    public GlobalState() {
        drivePerspective = DrivePerspectives.HATCH;
    }

    public void setPerspective(DrivePerspectives newPerspective) {
        drivePerspective = newPerspective;
    }

    public DrivePerspectives getPerspective() {
        return drivePerspective;
    }


}