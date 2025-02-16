package filehandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;
import java.util.ArrayList;

public class FileReader {
    private static ArrayList<Task> tasksToBeInitialized = new ArrayList<>();
    private static final String filePath = "//wsl.localhost/Ubuntu/home/jasonho/cs2113/ip/data/mike.txt";

    public static ArrayList<Task> getTasksToBeInitialized() throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String[] information = s.nextLine().split(" | ");
            String typeOfTask = information[0];
            String isMarked = information[1];
            String taskDescription = information[2];

            switch (typeOfTask) {
            case "T":
                Todo todoToBeAdded = new Todo(taskDescription);
                if (isMarked.equals("1")) {
                    todoToBeAdded.markAsDone();
                }
                tasksToBeInitialized.add(todoToBeAdded);
                break;
            case "D":
                Deadline deadline = new Deadline(taskDescription,information[3]);
                if (isMarked.equals("1")) {
                    deadline.markAsDone();
                }
                tasksToBeInitialized.add(deadline);
                break;
            case "E":
                String time = information[3];
                String[] fromAndTo = time.split(" to ");
                Event eventToBeAdded = new Event(taskDescription, fromAndTo[0], fromAndTo[1]);
                if (isMarked.equals("1")) {
                    eventToBeAdded.markAsDone();
                }
                tasksToBeInitialized.add(eventToBeAdded);
                break;
            }

        }

        return tasksToBeInitialized;
    }
}
