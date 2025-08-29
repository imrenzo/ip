package commands;
import bossexceptions.BossException;
import java.util.Objects;

/**
 * Contains methods that bot is able to perform.
 */
public enum CommandsEnum {
    BYE("bye", null),
    LIST("list", null),
    MARK("mark", null),
    UNMARK("unmark", null),
    TODO("todo", "T"),
    DEADLINE("deadline", "D"),
    EVENT("event", "E"),
    DELETE("delete", null);

    private final String COMMAND_NAME;
    private final String SHORT_CODE;

    /**
     * Contains methods that bot is able to perform.
     */
    CommandsEnum(String commandName, String shortCode) {
        this.COMMAND_NAME = commandName;
        this.SHORT_CODE = shortCode;
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
            if (Objects.equals(cmd.COMMAND_NAME, command)) {
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
            if (Objects.equals(cmd.SHORT_CODE, code)) {
                return fromString(cmd.COMMAND_NAME);
            }
        }
        throw new BossException("Invalid short code");
    }
}
