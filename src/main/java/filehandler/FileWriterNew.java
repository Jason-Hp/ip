package filehandler;

import task.Task;
import task.Deadline;
import task.Event;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class FileWriterNew {
    private static final String filePath = (new File("./data/mike.txt")).getAbsolutePath();
    public static void appendToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);

        String toBeWritten = "";
        int markOrUnmark = task.isDone() ? 1 : 0;
        String taskType = task.getSymbol();

        switch (taskType) {
        case "T":
            toBeWritten = "T | " + markOrUnmark + " | " + task.getDescription();
            break;
        case "D":
            Deadline deadline = (Deadline) task;
            toBeWritten = "D | " + markOrUnmark + " | " + task.getDescription().split(" \\(")[0] + " | " + deadline.getBy();
            break;
        case "E":
            Event event = (Event) task;
            toBeWritten = "E | " + markOrUnmark + " | " + task.getDescription().split(" \\(")[0] + " | from " + event.getFrom() + " to " + event.getTo();
            break;
        }

        fw.write(toBeWritten + System.lineSeparator());
        fw.close();
    }
}
