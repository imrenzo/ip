package ui;

import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles printing content to screen.
 */
public class Ui {
    // CHECKSTYLE.OFF: AbbreviationAsWordInName
    private static final String NAME = "Boss";
    private final Scanner SCANNER;
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    // CHECKSTYLE.ON: AbbreviationAsWordInName

    public Ui() {
        SCANNER = new Scanner(System.in);
    }

    public static String showWelcome() {
        String message = "Hello! I'm " + NAME + "\n" + "What can I do for you?";
        displayMessage(message);
        return message;
    }

    public void showLine() {
        displayMessage("_______");
    }

    public String readCommand() {
        return SCANNER.nextLine();
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        displayMessage(message);
    }

    public void exit() {
        displayMessage(EXIT_MESSAGE);
    }

    public String displayTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            String emptyMessage = "No tasks found";
            Ui.displayMessage(emptyMessage);
            return emptyMessage;
        }

        ArrayList<String> messages = new ArrayList<>();
        messages.add("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            String taskString = (i + 1) + ": " + tasks.get(i);
            Ui.displayMessage(taskString);
            messages.add(taskString);
        }
        return String.join("\n", messages);
    }
}