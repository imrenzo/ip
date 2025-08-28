package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import commands.CommandsEnum;
import task.Task;
import bossexceptions.BossException;
import task.TaskList;

/**
 * Contains methods to handle manipulation of txt file containing list of tasks.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads all task into a Task array from txt file in filePath.
     *
     * @return Task array containing tasks from filePath.
     * @throws BossException If txt in file has an invalid format.
     * @throws BossException If file specified in filePath does not exist.
     */
    public ArrayList<Task> loadFileContents() throws BossException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(this.filePath);
        try {
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String line = s.nextLine();
                String[] taskStr = line.split("\\|");
                if (taskStr.length != 3 || taskStr[0].isBlank() || taskStr[1].isBlank() || taskStr[2].isBlank()) {
                    throw new BossException("Invalid format for task " + line + " in loaded file.");
                }

                CommandsEnum taskType = CommandsEnum.fromShortCode(taskStr[0].trim());
                boolean isDone = Integer.parseInt(taskStr[1].trim()) == 1;
                String description = taskStr[2].trim();
                Task task = Task.parseTask(taskType, isDone, description);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new BossException("Error: Please create a boss.txt file under [project root]/data/boss.txt");
        }
        return tasks;
    }

    /**
     * Converts task to a format string to add into task file.
     *
     * @param task Task to be converted to a string.
     * @return Formatted string of task for task file.
     */
    private String convertTaskToString(Task task) {
        String taskStr = task.toString();
        String description = taskStr.substring(6).trim();
        String isDone = taskStr.charAt(4) == 'X' ? "1" : "0";
        String cmdType = task.getCommandCode();
        String[] arr = {cmdType, isDone, description};
        return String.join(" | ", arr) + "\n";
    }

    /**
     * Iterates though TaskList and writes all tasks into file.
     *
     * @param tasks array of tasks to add to file.
     * @throws BossException If IOException thrown by FileWriter object.
     */
    public void writeToFile(TaskList tasks) throws BossException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task task : tasks.list()) {
                String taskToString = convertTaskToString(task);
                fw.write(taskToString);
            }
            fw.close();
        } catch (IOException e) {
            throw new BossException(e.getMessage());
        }
    }
}
