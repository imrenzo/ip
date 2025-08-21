import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Boss {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String name = "Boss";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String cmdType = input.split(" ")[0];
            String removeCmd = String.join("", input.split(cmdType)).trim();

            switch (cmdType) {
                case "bye": {
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                }
                case "list": {
                    printTasks();
                    break;
                }
                case "mark": {
                    updateTaskStatus(removeCmd, true);
                    break;
                }
                case "unmark": {
                    updateTaskStatus(removeCmd, false);
                    break;
                }
                case "todo": {
                    Task task = new ToDos(removeCmd);
                    addTasks(task);
                    break;
                }
                case "deadline": {
                    String[] s = removeCmd.split("/by ", 2);
                    Task task = new Deadlines(s[0], s[1]);
                    addTasks(task);
                    break;
                }
                case "event": {
                    String[] s = removeCmd.split("/from ", 2);
                    Task task = new Events(s[0], s[1]);
                    addTasks(task);
                    break;
                }
                default: {
                    System.out.println("invalid command");
                    break;
                }
            }
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

    private static void updateTaskStatus(String indexStr, boolean isDone) {
        int index = Integer.parseInt(indexStr) - 1;
        if (index >= tasks.size()) {
            System.out.println("invalid index number");
            return;
        }
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
