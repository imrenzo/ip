package commands.others;

import commands.Command;
import commands.CommandsEnum;
import storage.Storage;
import ui.Ui;
import bossexceptions.BossException;
import task.Task;
import task.TaskList;

public class TaskCommand extends Command {
    private CommandsEnum command;
    private String taskInfo;

    public TaskCommand(CommandsEnum command, String taskInfo) {
        super(false);
        this.command = command;
        this.taskInfo = taskInfo;
    }

    /**
     * Adds a Task instance into TaskList.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui Ui instance of bot to display messages to the user.
     * @param storage The storage instance used by bot.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BossException {
        // Generate a child class object from Task type.
        Task task = Task.parseTask(command, taskInfo);

        // Add children instance object to list of tasks.
        tasks.add(task);
        ui.displayMessage("Got it. I've added this task:");
        ui.displayMessage(task.toString());
        ui.displayMessage("Now you have " + tasks.taskSize() + " tasks in the list.");
    }
}
