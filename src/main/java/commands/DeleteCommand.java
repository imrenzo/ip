package commands;

import Storage.Storage;
import Ui.Ui;
import bossexceptions.BossException;
import task.Task;
import task.TaskList;

public class DeleteCommand extends Command {
    private final String indexStr;

    public DeleteCommand(String indexStr) {
        super(false);
        this.indexStr = indexStr;
    }

    /**
     * Deletes task from tasks array.
     *
     * @param ui string format of index in tasks to remove element.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BossException {
        int index = tasks.validateTasksIndex(indexStr);
        Task task = tasks.get(index);
        tasks.remove(index);
        ui.displayMessage("Noted. I've removed this task:");
        ui.displayMessage(task.toString());
        ui.displayMessage("Now you have " + tasks.taskSize() + " tasks in the list.");
    }
}
