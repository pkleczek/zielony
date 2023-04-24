package pl.kleczek.zielony.atmservice;

public class Fixtures {
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
}
