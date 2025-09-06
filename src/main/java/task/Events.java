package task;

/**
 * Events: tasks that start at a specific date/time and ends at a specific date/time.
 */
public class Events extends Task {
    private static final String commandCode = "E";
    private final String fromDate;
    private final String toDate;

    /**
     * Initialises fields of class and parent class instances.
     */
    public Events(String description, String fromDate, String toDate) {
        super(description, commandCode);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "[" + commandCode + "]" + super.toString() + "(from: " + this.fromDate + " to: " + this.toDate + ")";
    }
}
