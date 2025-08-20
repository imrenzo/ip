import java.util.Scanner;

public class Boss {
    public static void main(String[] args) {
        String name = "Boss";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(input);

        while (!input.equals("bye")) {
            input = scanner.nextLine();
            System.out.println(input);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
