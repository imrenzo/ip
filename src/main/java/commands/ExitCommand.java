package commands;

import Storage.Storage;
import Ui.Ui;
import bossexceptions.BossException;
import task.TaskList;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws BossException {
        storage.writeToFile(tasks); // update file with updated tasks
    }
}
