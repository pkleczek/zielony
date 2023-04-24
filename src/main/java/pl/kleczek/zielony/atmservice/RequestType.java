package pl.kleczek.zielony.atmservice;

public enum RequestType {
    FAILURE_RESTART(0),
    PRIORITY(1),
    SIGNAL_LOW(2),
    STANDARD(3);

    public final int priority;

    RequestType(int priority) {
        this.priority = priority;
    }
}
