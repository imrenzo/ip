package task;

/**
 * ToDos: tasks without any date/time attached to it e.g., visit new theme park
 */
public class ToDos extends Task{
    private static final String commandString = "todo";

    public ToDos(String description) {
        super(description, commandString);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
