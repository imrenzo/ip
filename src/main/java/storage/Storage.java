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
    private final String FILE_PATH;

    /**
     * Sets file location in class instance.
     *
     * @param filePath File location of text file.
     */
    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    /**
     * Loads all task into a Task array from txt file.
     *
     * @return Task array.
     * @throws BossException If text in file has invalid format.
     * @throws BossException If file is not found.
     */
    public ArrayList<Task> loadFileContents() throws BossException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(this.FILE_PATH);
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
     * Iterates though tasks array and writes tasks into file
     *
     * @param tasks array of tasks to add to file.
     */
    public void writeToFile(TaskList tasks) throws BossException {
        try {
            FileWriter fw = new FileWriter(this.FILE_PATH);
            for (Task task : tasks.list()) {
                fw.write(task.getFileString());
            }
            fw.close();
        } catch (IOException e) {
            throw new BossException(e.getMessage());
        }
    }
}
