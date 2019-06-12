package frc.robot.logging;

import java.util.LinkedList;
import java.util.Queue;

public class LogOnceQueue {

    public interface LogCall {
        public void logData();
    }

    private Queue<LogCall> queue;
    private final int pace;
    private int counter = 0;
    private boolean enabled = false;

    public LogOnceQueue(int pace) {
        queue = new LinkedList<LogCall>();
        this.pace = pace;
    }

    public void enable() {
        enabled = true;
    }

    public void add(LogCall call) {
        queue.add(call);
    }

    public void run() {
        if(enabled && counter % pace == 0 && !queue.isEmpty()) {
            System.out.println("Logging something in queue");
            queue.element().logData();
            queue.remove();
        }
        counter++;
    }
}