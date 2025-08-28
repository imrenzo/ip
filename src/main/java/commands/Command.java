package commands;

import storage.Storage;
import ui.Ui;
import bossexceptions.BossException;
import task.TaskList;

public abstract class Command {
    private final boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the specific command logic for each Command type.
     *
     * @param tasks TaskList containing all tasks
     * @param ui Ui instance of bot to display messages to the user
     * @param storage The storage instance used by
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BossException;

    public boolean getExit() {
        return this.isExit;
    }
}
