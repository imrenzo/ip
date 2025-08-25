package commands;

import bossexceptions.BossException;
import commands.others.ListCommand;
import commands.others.TaskCommand;
import commands.task.DeleteCommand;
import commands.task.ExitCommand;
import commands.task.MarkCommand;

public class Parser {
    public static Command parse(String input) throws BossException {
        String cmdString = input.split(" ")[0];
        CommandsEnum command = CommandsEnum.fromString(cmdString);
        String removeCmd = String.join("", input.split(cmdString)).trim();

        switch (command) {
            case BYE -> {
                return new ExitCommand();
            }
            case LIST -> {
                return new ListCommand();
            }
            case MARK -> {
                return new MarkCommand(removeCmd, true);
            }
            case UNMARK -> {
                return new MarkCommand(removeCmd, false);
            }
            case TODO, DEADLINE, EVENT -> {
                return new TaskCommand(command, removeCmd);
            }
            case DELETE -> {
                return new DeleteCommand(removeCmd);
            }
            default -> throw new BossException("Invalid command");
        }
    }
}
