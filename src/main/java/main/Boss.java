package main;

import bossexceptions.BossException;
import commands.Command;
import commands.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Simulates a Personal Assistant Chatbot.
 */
public class Boss {
    // CHECKSTYLE.OFF: AbbreviationAsWordInName
    private final Storage STORAGE;
    private TaskList tasks;
    private final Ui UI;
    // CHECKSTYLE.ON: AbbreviationAsWordInName

    /**
     * Reads file and updates tasks with file contents.
     *
     * @param filePath file containing task strings.
     */
    private Boss(String filePath) {
        UI = new Ui();
        STORAGE = new Storage(filePath);
        try {
            tasks = new TaskList(STORAGE.loadFileContents());
        } catch (BossException e) {
            UI.showError(e.getMessage());
        }
    }

    /**
     * Runs the program.
     */
    private void run() {
        UI.showWelcome();
        boolean isExit = false;

        // continuously loops to handle user commands
        while (!isExit) {
            try {
                String fullCommand = UI.readCommand();
                // UI.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, UI, STORAGE);
                isExit = c.getExit();
                // UI.showLine();
            } catch (BossException e) {
                UI.showError("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                UI.showError("Error: Please enter a proper number");
            } catch (Exception e) {
                UI.showError("Unexpected error: " + e.getMessage());
            }
        }
        UI.exit();
    }

    public static void main(String[] args) {
        new Boss("data/boss.txt").run();
    }
}
