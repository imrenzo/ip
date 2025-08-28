package commands.task;

import commands.Command;
import storage.Storage;
import ui.Ui;
import bossexceptions.BossException;
import task.TaskList;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    /**
     * Write file in disk with tasks in TaskList.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui Ui instance of bot to display messages to the user.
     * @param storage The storage instance used by bot.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BossException {
        storage.writeToFile(tasks);
    }
}
