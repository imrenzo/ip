package commands.others;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import commands.Command;
import commands.CommandsEnum;
import ineffaexceptions.IneffaException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Gets text from help file and prints it out.
 */
public class HelpCommand extends Command {
    private final String mainPath = "src/main/resources/help/";
    private String fileName = "help.txt";

    /** Instantiates super class */
    public HelpCommand(String commandType) {
        super(false, CommandsEnum.HELP);
        if (!commandType.isEmpty()) {
            this.fileName = commandType + ".txt";
        }
    }

    /**
     * Prints help.txt text to UI
     *
     * @return All text in help.txt.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IneffaException {
        String path = this.mainPath + this.fileName;
        File f = new File(path);
        try {
            Scanner s = new Scanner(f);
            StringBuilder message = new StringBuilder();
            while (s.hasNext()) {
                String line = s.nextLine();
                System.out.println(line);
                message.append(line).append("\n");
            }
            return message.toString();
        } catch (FileNotFoundException e) {
            throw new IneffaException("Error: cannot find help.txt file in " + path);
        }
    }
}
