import java.util.ArrayList;
import java.util.Scanner;

public class Boss {
    public static void main(String[] args) {
        String name = "Boss";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        ArrayList<String> lst = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(lst);
            } else {
                lst.add(input);
                System.out.println("added: " + input);
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
