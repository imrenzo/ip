package task;

import bossexceptions.BossException;
import commands.CommandsEnum;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Contains information a task needs to have.
 */
public abstract class Task {
    private final String DESCRIPTION;
    private boolean isDone;
    private final String COMMAND_CODE;

    /**
     * Initialises fields of class.
     */
    public Task(String description, String commandString) {
        this.DESCRIPTION = description;
        this.COMMAND_CODE = String.valueOf(commandString.charAt(0)).toUpperCase();
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getCommandCode() {
        return this.COMMAND_CODE;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + DESCRIPTION;
    }

    /**
     * Creates task based on the type of task user wants to create.
     *
     * @param command type of task to create.
     * @param taskInfo description and dates (if required) of task.
     * @return Task task.
     * @throws BossException If invalid format for parameters.
     */
    public static Task parseTask(CommandsEnum command, String taskInfo) throws BossException {
        if (taskInfo.isBlank()) {
            throw new BossException("Please enter a description for a " + command + " task.");
        }
        switch (command) {
            case TODO -> {
                return new ToDos(taskInfo);
            }
            case DEADLINE -> {
                String[] s = taskInfo.split("/by ", 2);
                if (s.length < 2 || s[0].isBlank() || s[1].isBlank()) {
                    throw new BossException("Invalid format for deadline task.");
                }
                String description = s[0].trim();
                String dateAndTime = s[1].trim();

                String[] dateAndTimeSplit = dateAndTime.split(" ");
                if (dateAndTimeSplit.length < 2 || dateAndTimeSplit[0].isBlank() || dateAndTimeSplit[1].isBlank()) {
                    throw new BossException("Invalid format for date and time.");
                }

                String time = dateAndTimeSplit[1].trim();

                try {
                    // Date format string is in dd-MM-yyyy
                    // However task print out in yyyy-MM-dd
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate date =  LocalDate.parse(
                            dateAndTimeSplit[0].replace("/", "-")
                                    .trim(), formatter);
                    return new Deadlines(description, date, time);
                } catch (DateTimeParseException e) {
                    throw new BossException("Invalid format for date and time.");
                }
            }
            case EVENT -> {
                String[] s = taskInfo.split("/from ", 2);
                if (s.length < 2 || s[0].isBlank() || s[1].isBlank()) {
                    throw new BossException("Invalid format for event task.");
                }
                String description = s[0];
                String[] dates = s[1].split("/to", 2);
                if (dates.length < 2 || dates[0].isBlank() || dates[1].isBlank()) {
                    throw new BossException("Invalid format for start and end date/timings.");
                }
                String fromDate = dates[0].trim();
                String toDate = dates[1].trim();
                return new Events(description, fromDate, toDate);
            }
            default -> throw new BossException("unrecognised cmd type: " + command);
        }
    }

    /**
     * Creates task based on the type of task user wants to create & marks as done if specified.
     *
     * @param command type of task to create.
     * @param isDone mark task as done if true
     * @param taskInfo description and dates (if required) of task.
     * @return Task task.
     * @throws BossException If invalid format for parameters.
     */
    public static Task parseTask(CommandsEnum command, boolean isDone, String taskInfo) throws BossException {
        Task task = parseTask(command, taskInfo);
        if (isDone) {
            task.setDone();
        }
        return task;
    }

    /**
     * Checks if task DESCRIPTION contains description
     * @param description Description method caller wants to find
     * @return boolean
     */
    public boolean containsDecription(String description) {
        return this.DESCRIPTION.contains(description);
    }
}
