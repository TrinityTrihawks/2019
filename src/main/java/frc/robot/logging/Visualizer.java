package frc.robot.logging;

import java.util.Set;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Visualizer {

    // PARAMETERS: must agree with client app
    final String tableName = "Visualizer";
    final String xAxisName = "xaxis";


    NetworkTable table = NetworkTableInstance.getDefault().getTable(tableName);

    public void clear() {
        clearTable(table);
    }

    public void push(String name, double[] data) {
        table.getEntry(name).setDoubleArray(data);
    }

    public void setXAxis(String name, double[] data) {
        NetworkTable subtable = table.getSubTable(xAxisName);
        clearTable(subtable);
        subtable.getEntry(name).setDoubleArray(data);

    }

    private void clearTable(NetworkTable tableToClear) {
        Set<String> keys = tableToClear.getKeys();
        for(String key : keys) {
            tableToClear.delete(key);
        }
    }

}