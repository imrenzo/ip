package commands;

import bossexceptions.BossException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Abstract class containing methods that user Command children need to have.
 */
public abstract class Command {
    // CHECKSTYLE.OFF: AbbreviationAsWordInName
    private final boolean IS_EXIT;
    // CHECKSTYLE.ON: AbbreviationAsWordInName

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
