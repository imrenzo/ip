package task;

import bossexceptions.BossException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
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
        if (index >= this.tasks.size() || index < 0) {
            throw new BossException("Invalid index number");
        }
        return index;
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }

    public int taskSize() {
        return tasks.size();
    }

    public ArrayList<Task> list() {
        return new ArrayList<>(this.tasks);
    }

    public void printAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks created");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ": " + tasks.get(i));
        }
    }
}
