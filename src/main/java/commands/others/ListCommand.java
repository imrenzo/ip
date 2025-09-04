package commands.others;

import bossexceptions.BossException;
import commands.Command;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * List all tasks created by user.
 */
public class ListCommand extends Command {
    /**
     * Sets isExit as false in Command task.
     * So that program does not exit on ListCommand.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Gets all elements in tasks array.
     *
     * @return Message from executing task.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BossException {
        ArrayList<Task> tasks = taskList.getAllTasks("");
        return ui.displayTasks(tasks);
    }
}
