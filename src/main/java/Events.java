/**
 * Events: tasks that start at a specific date/time and ends at a specific date/time.
 */
public class Events extends Task{
    private final String fromDate;
    private final String toDate;

    public Events(String description, String fromDate, String toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.fromDate + " to: " + this.toDate + ")";
    }
}
