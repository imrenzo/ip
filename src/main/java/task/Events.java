package task;

/**
 * Events: tasks that start at a specific date/time and ends at a specific date/time.
 */
public class Events extends Task{
    private final String FROM_DATE;
    private final String TO_DATE;
    private static final String COMMAND_CODE = "E";

    /**
     * Initialises fields of class and parent class instances.
     */
    public Events(String description, String fromDate, String toDate) {
        super(description, COMMAND_CODE);
        this.FROM_DATE = fromDate;
        this.TO_DATE = toDate;
    }

    @Override
    public String toString() {
        return "[" + COMMAND_CODE + "]" + super.toString() + "(from: " + this.FROM_DATE + " to: " + this.TO_DATE + ")";
    }
}
