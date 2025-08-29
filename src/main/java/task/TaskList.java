package task;

import bossexceptions.BossException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> TASKS;

    /**
     * Sets the single TaskList contained in program instance.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.TASKS = tasks;
    }

    /**
     * Returns int value of indexStr - 1
     * As 1-based indexing used by user commands
     * Validates that indexStr can be parsed as an int.
     * Validates that index can be found in tasks array.
     *
     * @param indexStr string format of index in tasks.
     * @return int value of valid indexStr
     * @throws NumberFormatException If indexStr cannot be parsed as int
     * @throws BossException If index value < 0 or greater than tasks array size.
     */
    public int validateTasksIndex(String indexStr) throws BossException, NumberFormatException {
        int index = Integer.parseInt(indexStr) - 1;
        if (index >= this.TASKS.size() || index < 0) {
            throw new BossException("Invalid index number");
        }
        return index;
    }

    public Task get(int index) {
        return this.TASKS.get(index);
    }

    public void add(Task task) {
        TASKS.add(task);
    }

    public void remove(int index) {
        this.TASKS.remove(index);
    }

    public int taskSize() {
        return TASKS.size();
    }

    public ArrayList<Task> list() {
        return new ArrayList<>(this.TASKS);
    }

    /**
     * Display all tasks in TaskList for user.
     */
    public void printAllTasks() {
        if (TASKS.isEmpty()) {
            System.out.println("No tasks created");
        }
        for (int i = 0; i < TASKS.size(); i++) {
            System.out.println((i + 1) + ": " + TASKS.get(i));
        }
    }
}
