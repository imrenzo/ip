import java.util.ArrayList;
import java.util.Scanner;

public class Boss {
    public static void main(String[] args) {
        String name = "Boss";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ": " + tasks.get(i));
                }
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                Task currentTask = tasks.get(index);
                currentTask.setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(currentTask);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                Task currentTask = tasks.get(index);
                currentTask.setNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(currentTask);
            } else {
                tasks.add(new Task(input));
                System.out.println("added: " + input);
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
