import java.util.ArrayList;
import java.util.Scanner;

public class Boss {
    public static void main(String[] args) {
        String name = "Boss";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String cmdType = input.split(" ")[0];
            switch (cmdType) {
                case "bye": {
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                }
                case "list": {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ": " + tasks.get(i));
                    }
                    break;
                }
                case "mark": {
                    int index = Integer.parseInt(input.substring(5)) - 1; // remove "mark "
                    Task currentTask = tasks.get(index);
                    currentTask.setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(currentTask);
                    break;
                }
                case "unmark": {
                    int index = Integer.parseInt(input.substring(7)) - 1; // remove "unmark "
                    Task currentTask = tasks.get(index);
                    currentTask.setNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(currentTask);
                    break;
                }
                default: {
                    tasks.add(new Task(input));
                    System.out.println("added: " + input);
                }
            }
        }
    }
}
