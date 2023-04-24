package pl.kleczek.zielony.atmservice;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fixtures {
    private static final SecureRandom random = new SecureRandom();

    public static Task failureRestart(int region, int atmId) {
        return new Task(region, RequestType.FAILURE_RESTART, atmId);
    }

    public static Task priority(int region, int atmId) {
        return new Task(region, RequestType.PRIORITY, atmId);
    }

    public static Task signalLow(int region, int atmId) {
        return new Task(region, RequestType.SIGNAL_LOW, atmId);
    }

    public static Task standard(int region, int atmId) {
        return new Task(region, RequestType.STANDARD, atmId);
    }

    public static Visit aVisit(int region, int atmId) {
        return new Visit(region, atmId);
    }

    public static List<Task> randomTasks(int numberOfRegions, int tasksPerRegion, int atmsInRegion) {
        RequestType[] requestTypes = RequestType.values();
        List<Task> tasks = new ArrayList<>(numberOfRegions * tasksPerRegion);

        for (int region = 0; region < numberOfRegions; region++) {
            for (int task = 0; task < tasksPerRegion; task++) {
                int atmId = random.nextInt(1, atmsInRegion + 1);
                RequestType requestType = requestTypes[random.nextInt(1000) % requestTypes.length];
                tasks.add(new Task(region + 1, requestType, atmId));
            }
        }

        Collections.shuffle(tasks);
        return tasks;
    }
}
