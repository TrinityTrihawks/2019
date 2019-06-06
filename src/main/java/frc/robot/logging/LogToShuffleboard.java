package frc.robot.logging;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * One implementation of LogExecutor. Logs data to Shuffleboard.
 */
public class LogToShuffleboard implements LogExecutor {

    public void log(String caption, Object data) {
        if(data instanceof Integer || data instanceof Double) {
            double numData = (double) data;
            SmartDashboard.putNumber(caption, numData);

        } else if(data instanceof String) {
            String stringData = (String) data;
            SmartDashboard.putString(caption, stringData);

        } else if(data instanceof Boolean) {
            boolean boolData = (boolean) data;
            SmartDashboard.putBoolean(caption, boolData);

        } else if(data instanceof Sendable) {
            Sendable sendableData = (Sendable) data;
            SmartDashboard.putData(sendableData);
        }

    }

}