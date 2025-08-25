/**
 * ToDos: tasks without any date/time attached to it e.g., visit new theme park
 */
public class ToDos extends Task{
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
