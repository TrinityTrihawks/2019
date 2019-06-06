package frc.robot.logging;

/**
 * An interface responsible for actually logging messages provided by the ContinuousLogger class
 */
public interface LogExecutor{

    public void log(String caption, Object data);

}