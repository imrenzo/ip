package commands.task;

import commands.Command;
import storage.Storage;
import ui.Ui;
import bossexceptions.BossException;
import task.Task;
import task.TaskList;

public class MarkCommand extends Command {
    private final boolean isDone;
    private final String indexStr;

    public MarkCommand(String indexStr, boolean isDone) {
        super(false);
        this.isDone = isDone;
        this.indexStr = indexStr;
    }

    /**
     * Creates task based on the type of task user wants to create.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BossException {
        int index = tasks.validateTasksIndex(indexStr);
        Task currentTask = tasks.get(index);
        if (isDone) {
            currentTask.setDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            currentTask.setNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(currentTask);
    }
}
