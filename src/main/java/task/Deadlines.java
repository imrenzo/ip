package task;

import java.time.LocalDate;

/**
 * Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
 */
public class Deadlines extends Task {
    // CHECKSTYLE.OFF: AbbreviationAsWordInName
    private static final String COMMAND_CODE = "D";
    private final LocalDate DATE;
    private final String TIME;
    // CHECKSTYLE.ON: AbbreviationAsWordInName

    /**
     * Initialises fields of class and parent class instances.
     */
    public Deadlines(String description, LocalDate date, String time) {
        super(description, COMMAND_CODE);
        this.DATE = date;
        this.TIME = time;
    }

    @Override
    public String toString() {
        // date printed out in yyyy-MM-dd format
        return "[" + COMMAND_CODE + "]" + super.toString() + " (by: " + this.DATE + " " + this.TIME + ")";
    }
}
