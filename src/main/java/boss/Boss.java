package boss;

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
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private boolean isExit = false;

    /**
     * Reads file and updates tasks with file contents.
     *
     * @param filePath file containing task strings.
     */
    public Boss(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFileContents());
        } catch (BossException e) {
            ui.showError(e.getMessage());
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
                String fullCommand = ui.readCommand();
                // ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.getExit();
                // ui.showLine();
            } catch (BossException e) {
                ui.showError("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError("Error: Please enter a proper number");
            } catch (Exception e) {
                ui.showError("Unexpected error: " + e.getMessage());
            }
        }
        ui.exit();
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
            // ui.showLine();
            Command c = Parser.parse(input);
            // ui.showLine();
            return c.execute(tasks, ui, storage);
        } catch (BossException e) {
            errorMessage = "Error: " + e.getMessage();
        } catch (NumberFormatException e) {
            errorMessage = "Error: Please enter a proper number";
        } catch (Exception e) {
            errorMessage = "Unexpected error: " + e.getMessage();
        }
        ui.showError(errorMessage);
        return errorMessage;
    }
}
