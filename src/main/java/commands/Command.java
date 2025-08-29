package commands;

import storage.Storage;
import ui.Ui;
import bossexceptions.BossException;
import task.TaskList;

public abstract class Command {
    private final boolean IS_EXIT;

    public Command(boolean isExit) {
        this.IS_EXIT = isExit;
    }

    /**
     * Performs logic for different command types.
     *
     * @param tasks List of tasks.
     * @param ui Ui provides ability to display text on screen.
     * @param storage Storage provides file handling methods
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BossException;

    public boolean getExit() {
        return this.IS_EXIT;
    }
}
