package commands;
import bossexceptions.BossException;
import java.util.Objects;

public enum CommandsEnum {
    BYE("bye", null),
    LIST("list", null),
    MARK("mark", null),
    UNMARK("unmark", null),
    TODO("todo", "T"),
    DEADLINE("deadline", "D"),
    EVENT("event", "E"),
    DELETE("delete", null);

    private final String commandName;
    private final String shortCode;

    CommandsEnum(String commandName, String shortCode) {
        this.commandName = commandName;
        this.shortCode = shortCode;
    }

    /**
     * Returns CmdType based on command passed by user.
     *
     * @param command string passed by user.
     * @return CmdType
     * @throws BossException If command type invalid
     */
    public static CommandsEnum fromString(String command) throws BossException {
        for (CommandsEnum cmd : CommandsEnum.values()) {
            if (Objects.equals(cmd.commandName, command)) {
                return cmd;
            }
        }
        throw new BossException("Invalid command");
    }

    /**
     * Returns CmdType based on short code of Task.
     *
     * @param code short code of task.
     * @return long code of task
     * @throws BossException If command type invalid
     */
    public static CommandsEnum fromShortCode(String code) throws BossException {
        for (CommandsEnum cmd : CommandsEnum.values()) {
            if (Objects.equals(cmd.shortCode, code)) {
                return fromString(cmd.commandName);
            }
        }
        throw new BossException("Invalid short code");
    }
}
