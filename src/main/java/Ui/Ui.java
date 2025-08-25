package Ui;

import java.util.Scanner;

public class Ui {
    private static final String name = "Boss";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }

    public void showLine() {
        System.out.println("_______");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
