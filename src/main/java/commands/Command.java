package commands;

import Storage.Storage;
import Ui.Ui;
import bossexceptions.BossException;
import task.TaskList;

public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BossException;

    public boolean getExit() {
        return this.isExit;
    }

    public void exitProgram() {
        this.isExit = true;
    }
}
