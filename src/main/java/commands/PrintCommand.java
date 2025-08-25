package commands;

import Storage.Storage;
import Ui.Ui;
import bossexceptions.BossException;
import task.TaskList;

public class PrintCommand extends Command {
    public PrintCommand() {
        super(false);
    }

    /**
     * Prints all elements in tasks array, adhering to a format.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BossException {
        ui.displayMessage("Here are the tasks in your list:");
        tasks.printAllTasks();
    }
}
