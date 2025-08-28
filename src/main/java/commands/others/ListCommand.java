package commands.others;

import commands.Command;
import storage.Storage;
import ui.Ui;
import bossexceptions.BossException;
import task.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    /**
     * Prints all elements in tasks array to the terminal.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui Ui instance of bot to display messages to the user.
     * @param storage The storage instance used by bot.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage("Here are the tasks in your list:");
        tasks.printAllTasks();
    }
}
