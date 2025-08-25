package task;

import java.time.LocalDate;

/**
 * Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
 */
public class Deadlines extends Task{
    private final LocalDate date;
    private final String time;
    private static final String commandString = "deadline";

    public Deadlines(String description, LocalDate date, String time) {
        super(description, commandString);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        // date printed out in yyyy-MM-dd format
        return "[D]" + super.toString() + " (by: " + this.date + " " + this.time + ")";
    }
}
