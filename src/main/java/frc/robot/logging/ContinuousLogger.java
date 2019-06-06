package frc.robot.logging;

import java.util.ArrayList;
import java.util.List;

public class ContinuousLogger {

    public interface DataProvider {
        public Object getData();
    }

    private class Entry {
        public final String caption;
        public final DataProvider dataProvider;
        public final int frequency;

        private Entry(String caption, DataProvider methodToLog, int frequency) {
            this.caption = caption;
            this.dataProvider = methodToLog;
            this.frequency = frequency;
        }
    }

    List<Entry> entries = new ArrayList<Entry>();    
    LogExecutor[] executors;

    int counter = 0;

    public ContinuousLogger(LogExecutor ... executors) {
        this.executors = executors;
    }

    public void add(String caption, DataProvider getData, int frequency) {
        entries.add(new Entry(caption, getData, frequency));
    }

    public void run() {
        for(Entry entry : entries) {
            if(counter % entry.frequency == 0) {

                for(LogExecutor executor : executors) {
                    executor.log(entry.caption, entry.dataProvider.getData());
                }
            }
        }

        // Update Loop count
        counter += 1;
    }

}