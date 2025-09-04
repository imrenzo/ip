package commands.others;

import commands.Command;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * Finds list of tasks that user wants to find based on description.
 */
public class FindCommand extends Command {
    // CHECKSTYLE.OFF: AbbreviationAsWordInName
    private final String TO_FIND;
    // CHECKSTYLE.ON: AbbreviationAsWordInName
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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.getAllTasks(TO_FIND);
        return ui.displayTasks(tasks);
    }
}
