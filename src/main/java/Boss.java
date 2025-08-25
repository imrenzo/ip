import Storage.Storage;
import Ui.Ui;
import commands.Command;
import commands.Parser;
import bossexceptions.BossException;
import task.TaskList;

/**
 * Simulates a Personal Assistant Chatbot.
 */
public class Boss {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private Boss(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFileContents());
        } catch (BossException e) {
            ui.showError("Error: " + e.getMessage());
        }
    }

    private void run() {
        ui.showWelcome();
        boolean isExit = false;

        // continuously loops to handle user commands
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
//                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.getExit();
//                ui.showLine();
            } catch(BossException e){
                ui.showError("Error: " + e.getMessage());
            } catch(NumberFormatException e){
                ui.showError("Error: Please enter a proper number");
            } catch(Exception e){
                ui.showError("Unexpected error: " + e.getMessage());
            }
        }
        ui.exit();
    }

    public static void main(String[] args) {
        new Boss("data/boss.txt").run();
    }
}
