package task;

/**
 * Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
 */
public class Deadlines extends Task{
    private final String date;
    private static final String commandString = "deadline";

    public Deadlines(String description, String date) {
        super(description, commandString);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.date + ")";
    }
}
