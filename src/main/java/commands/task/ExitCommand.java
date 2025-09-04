package commands.task;

import bossexceptions.BossException;
import commands.Command;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Task when user wants to exit program
 */
public class ExitCommand extends Command {
    /**
     * Instantiates command by telling super class that this is exit command.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Writes tasks to text file.
     *
     * @return Message from executing task
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BossException {
        storage.writeToFile(tasks); // update file with updated tasks
        return Ui.EXIT_MESSAGE;
    }
}
