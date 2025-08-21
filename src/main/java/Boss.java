import java.util.ArrayList;
import java.util.Scanner;

public class Boss {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    private enum CmdType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;

        private static CmdType fromString(String command) throws BossException {
            try {
                return CmdType.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new BossException("Invalid command");
            }
        }
    }

    public static void main(String[] args) {
        String name = "Boss";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                String cmdString = input.split(" ")[0];
                CmdType cmdType = CmdType.fromString(cmdString);
                String removeCmd = String.join("", input.split(cmdString)).trim();

                switch (cmdType) {
                    case BYE: {
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
                        Task todoTask = parseTask(cmdString, removeCmd);
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
            } catch (BossException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a proper number");
            }catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    private static void deleteTask(String indexStr) throws BossException {
        int index = validateTasksIndex(indexStr);
        Task task = tasks.get(index);
        tasks.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static int validateTasksIndex(String indexStr) throws BossException {
        int index = Integer.parseInt(indexStr) - 1;
        if (index >= tasks.size() || index < 0) {
            throw new BossException("Invalid index number");
        }
        return index;
    }

    private static Task parseTask(String cmdType, String taskInfo) throws BossException {
        if (taskInfo.isBlank()) {
            throw new BossException("Please enter a description for a " + cmdType + " task.");
        }
        switch (cmdType) {
            case "todo": {
                return new ToDos(taskInfo);
            }
            case "deadline": {
                String[] s = taskInfo.split("/by ", 2);
                if (s.length < 2 || s[0].isBlank() || s[1].isBlank()) {
                    throw new BossException("Invalid format for deadline task.");
                }
                return new Deadlines(s[0], s[1]);
            }
            case "event": {
                String[] s = taskInfo.split("/from ", 2);
                if (s.length < 2 || s[0].isBlank() || s[1].isBlank()) {
                    throw new BossException("Invalid format for event task.");
                }
                String description = s[0];
                String[] dates = s[1].split("/to", 2);
                if (dates.length < 2 || dates[0].isBlank() || dates[1].isBlank()) {
                    throw new BossException("Invalid format for start and end date/timings.");
                }
                String fromDate = dates[0].trim();
                String toDate = dates[1].trim();
                return new Events(description, fromDate, toDate);
            }
            default:
                throw new BossException("unrecognised cmd type: " + cmdType);
        }
    }

    private static void addTasks(Task task) {
        System.out.println("Got it. I've added this task:");
        tasks.add(task);
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ": " + tasks.get(i));
        }
    }

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
