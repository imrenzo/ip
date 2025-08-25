import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import task.Task;
import bossexceptions.BossException;

/**
 * Contains methods to handle manipulation of txt file containing list of tasks.
 */
public class Data {
    /**
     * Loads all task into a Task array from txt file.
     *
     * @param filePath file location.
     * @return Task array.
     * @throws FileNotFoundException If invalid format for indexStr.
     */
    public static ArrayList<Task> loadFileContents(String filePath) throws FileNotFoundException, BossException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] taskStr = line.split("\\|");
            if (taskStr.length != 3 || taskStr[0].isBlank() || taskStr[1].isBlank() || taskStr[2].isBlank()) {
                throw new BossException("Invalid format for task " + line + " in loaded file.");
            }

            Boss.CmdType taskType = Boss.CmdType.fromShortCode(taskStr[0].trim());
            boolean isDone = Integer.parseInt(taskStr[1].trim()) == 1;
            String description = taskStr[2].trim();
            Task task = Task.parseTask(taskType, isDone, description);
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Converts task to a suitable string to add into task file
     *
     * @param task Task
     * @return Suitable string format of task.
     */
    private static String convertTaskToString(Task task) {
        String taskStr = task.toString();
        String description = taskStr.substring(6).trim();
        String isDone = taskStr.charAt(4) == 'X' ? "1" : "0";
        String cmdType = task.getCommandCode();
        String[] arr = {cmdType, isDone, description};
        return String.join(" | ", arr) + "\n";
    }

    /**
     * Iterates though tasks array and writes tasks into file
     *
     * @param filePath file to write to.
     * @param tasks array of tasks to add to file.
     * @throws IOException If error when writing to file
     */
    public static void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            String taskToString = convertTaskToString(task);
            fw.write(taskToString);
        }
        fw.close();
    }
}
