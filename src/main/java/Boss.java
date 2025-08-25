import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import task.Task;
import bossexceptions.BossException;

/**
 * Simulates a Personal Assistant Chatbot.
 */
public class Boss {
    private static ArrayList<Task> tasks;
    private static final String filePath = "data/boss.txt";
    private static final String name = "Boss";

    public enum CmdType {
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

        CmdType(String commandName, String shortCode) {
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
        private static CmdType fromString(String command) throws BossException {
            for (CmdType cmd : CmdType.values()) {
                if (Objects.equals(cmd.commandName, command)) {
                    return cmd;
                }
            }
            throw new BossException("Invalid command");
        }

        /**
         * Converts short form of Task into their long code.
         *
         * @param code short code of task.
         * @return long code of task
         * @throws BossException If command type invalid
         */
        public static String shortCodeToLongCode(String code) throws BossException {
            for (CmdType cmd : CmdType.values()) {
                if (Objects.equals(cmd.shortCode, code)) {
                    return cmd.commandName;
                }
            }
            throw new BossException("Invalid short code");
        }
    }

    public static void main(String[] args) {
        try {
            // loads tasks saved on disk into tasks array
            tasks = Data.loadFileContents(filePath);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Hello! I'm " + name);
            System.out.println("What can I do for you?");

            // continuously loops to handle user commands
            while (true) {
                String input = scanner.nextLine();
                String cmdString = input.split(" ")[0];
                CmdType cmdType = CmdType.fromString(cmdString);
                String removeCmd = String.join("", input.split(cmdString)).trim();

                switch (cmdType) {
                    case BYE: {
                        Data.writeToFile(filePath, tasks); // update file with updated tasks
                        System.out.println("Bye. Hope to see you again soon!");
                        return;
                    }
                    case LIST: {
                        printTasks();
                        break;
                    }
                    case MARK: {
                        updateTaskStatus(removeCmd, true);
                        break;
                    }
                    case UNMARK: {
                        updateTaskStatus(removeCmd, false);
                        break;
                    }
                    case TODO, DEADLINE, EVENT: {
                        Task todoTask = Task.parseTask(cmdString, removeCmd);
                        addTasks(todoTask);
                        break;
                    }
                    case DELETE: {
                        deleteTask(removeCmd);
                        break;
                    }
                    default: {
                        throw new BossException("Invalid command");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Please create a boss.txt file under [project root]/data/boss.txt");
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error updating ./data/boss.txt: " + e.getMessage());
        } catch (BossException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a proper number");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Deletes task from tasks array.
     *
     * @param indexStr string format of index in tasks to remove element.
     * @throws BossException If index value < 0 or greater than tasks array size.
     * @throws BossException If invalid format for indexStr.
     */
    private static void deleteTask(String indexStr) throws BossException {
        int index = validateTasksIndex(indexStr);
        Task task = tasks.get(index);
        tasks.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Returns int value of indexStr
     * Validates that indexStr can be parsed as an int.
     * Validates that index can be found in tasks array.
     *
     * @param indexStr string format of index in tasks.
     * @return int value of valid indexStr
     * @throws NumberFormatException If indexStr cannot be parsed as int
     * @throws BossException If index value < 0 or greater than tasks array size.
     */
    private static int validateTasksIndex(String indexStr) throws BossException, NumberFormatException {
        int index = Integer.parseInt(indexStr) - 1;
        if (index >= tasks.size() || index < 0) {
            throw new BossException("Invalid index number");
        }
        return index;
    }

    /**
     * Adds a task to tasks array.
     *
     * @param task type of task to create.
     */
    private static void addTasks(Task task) throws BossException {
        System.out.println("Got it. I've added this task:");
        tasks.add(task);
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints all elements in tasks array, adhering to a format.
     */
    private static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ": " + tasks.get(i));
        }
    }

    /**
     * Creates task based on the type of task user wants to create.
     *
     * @param indexStr type of task to create.
     * @param isDone boolean of whether to mark (true) or unmark (false) task.
     * @throws BossException If invalid format for indexStr.
     */
    private static void updateTaskStatus(String indexStr, boolean isDone) throws BossException {
        int index = validateTasksIndex(indexStr);
        Task currentTask = tasks.get(index);
        if (isDone) {
            currentTask.setDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            currentTask.setNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(currentTask);
    }
}
