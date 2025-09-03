package commands.others;

import bossexceptions.BossException;
import commands.Command;
import storage.Storage;
import task.TaskList;
import ui.Ui;

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
     * Prints all elements in tasks array, adhering to a format.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BossException {
        ui.displayMessage("Here are the tasks in your list:");
        tasks.printAllTasks("");
    }
}
