package task;
import bossexceptions.BossException;

/**
 * Contains information a task needs to have.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;
    private final String commandCode;

    public Task(String description, String commandString) {
        this.description = description;
        this.commandCode = String.valueOf(commandString.charAt(0)).toUpperCase();
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
        return this.commandCode;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }

    /**
     * Creates task based on the type of task user wants to create.
     *
     * @param cmdType type of task to create.
     * @param taskInfo description and dates (if required) of task.
     * @return Task task.
     * @throws BossException If invalid format for parameters.
     */
    public static Task parseTask(Boss.CmdType cmdType, String taskInfo) throws BossException {
        if (taskInfo.isBlank()) {
            throw new BossException("Please enter a description for a " + cmdType + " task.");
        }
        switch (cmdType) {
            case TODO: {
                return new ToDos(taskInfo);
            }
            case DEADLINE: {
                String[] s = taskInfo.split("/by ", 2);
                if (s.length < 2 || s[0].isBlank() || s[1].isBlank()) {
                    throw new BossException("Invalid format for deadline task.");
                }
                return new Deadlines(s[0], s[1]);
            }
            case EVENT: {
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
            default:
                throw new BossException("unrecognised cmd type: " + cmdType);
        }
    }

    /**
     * Creates task based on the type of task user wants to create & marks as done if specified.
     *
     * @param cmdType type of task to create.
     * @param isDone mark task as done if true
     * @param taskInfo description and dates (if required) of task.
     * @return Task task.
     * @throws BossException If invalid format for parameters.
     */
    public static Task parseTask(Boss.CmdType cmdType, boolean isDone, String taskInfo) throws BossException {
        Task task = parseTask(cmdType, taskInfo);
        if (isDone) {
            task.setDone();
        }
        return task;
    }
}
