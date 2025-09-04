package boss;

import bossexceptions.BossException;
import commands.Command;
import commands.Parser;
import javafx.application.Platform;
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
    private boolean isExit = false;
    // CHECKSTYLE.ON: AbbreviationAsWordInName

    /**
     * Reads file and updates tasks with file contents.
     *
     * @param filePath file containing task strings.
     */
    public Boss(String filePath) {
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
        Ui.showWelcome();

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

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String errorMessage = "";
        try {
            // UI.showLine();
            Command c = Parser.parse(input);
            // UI.showLine();
            return c.execute(tasks, UI, STORAGE);
        } catch (BossException e) {
            errorMessage = "Error: " + e.getMessage();
        } catch (NumberFormatException e) {
            errorMessage = "Error: Please enter a proper number";
        } catch (Exception e) {
            errorMessage = "Unexpected error: " + e.getMessage();
        }
        UI.showError(errorMessage);
        return errorMessage;
    }
}
