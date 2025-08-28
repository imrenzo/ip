package commands.task;

import commands.Command;
import storage.Storage;
import ui.Ui;
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
     * Remove a task from TaskList.
     * Task obtained from indexStr position in TaskList.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui Ui instance of bot to display messages to the user.
     * @param storage The storage instance used by bot.
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
