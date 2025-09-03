package task;

/**
 * ToDos: tasks without any date/time attached to it e.g., visit new theme park
 */
public class ToDos extends Task{
    private static final String COMMAND_CODE = "T";

    /**
     * Initialises fields of class and parent class instances.
     */
    public ToDos(String description) {
        super(description, COMMAND_CODE);
    }

    @Override
    public String toString() {
        return "[" + COMMAND_CODE + "]" + super.toString();
    }
}
