package filehandler;

import task.Task;
import task.Deadline;
import task.Event;
import java.io.FileWriter;
import java.io.IOException;

public class fileWrite {
    private static final String filePath = "//wsl.localhost/Ubuntu/home/jasonho/cs2113/ip/data/mike.txt";

    public static void appendToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        String toBeWritten = "";
        int markOrUnmark = 0;
        if (task.isDone()){
            markOrUnmark = 1;
        }
        String taskType = task.getSymbol();
        switch (taskType) {
        case "T":
            toBeWritten = "T | "+markOrUnmark+" | "+task.getDescription();
            fw.write(toBeWritten);
            break;
        case "D":
            Deadline deadline = (Deadline) task;
            toBeWritten = "D | "+markOrUnmark+" | "+task.getDescription()+" | "+deadline.getBy();
            fw.write(toBeWritten);
            break;
        case "E":
            Event event = (Event) task;
            toBeWritten =  "E | "+markOrUnmark+" | "+task.getDescription()+" | from "+event.getFrom()+" to "+event.getTo();
            fw.write(toBeWritten);
            break;
        }
        fw.close();
    }
}
