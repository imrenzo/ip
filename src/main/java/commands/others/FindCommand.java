package commands.others;

import commands.Command;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Finds list of tasks that user wants to find based on description.
 */
public class FindCommand extends Command {
    private final String TO_FIND;
    /**
     * Sets description of task user wants to find.
     *
     * @param toFind Description of task.
     */
    public FindCommand(String toFind) {
        super(false);
        this.TO_FIND = toFind;
    }

    /**
     * Prints all elements in tasks array, adhering to a format.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage("Here are the matching tasks in your list:");
        tasks.printAllTasks(TO_FIND);
    }
}
