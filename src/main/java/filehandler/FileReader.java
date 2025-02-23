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
    private ArrayList<Task> tasksToBeInitialized;
    private String filePath;

    public FileReader(String filePath) {
        this.filePath = (new File(filePath)).getAbsolutePath();
    }

    public ArrayList<Task> getTasksToBeInitialized() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        tasksToBeInitialized = new ArrayList<>();
        while (s.hasNext()) {
            String[] information = s.nextLine().split(" \\| ");
            String typeOfTask = information[0];
            String isMarked = information[1];
            String taskDescription = information[2];

            //Reading data from file to populate the task list
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
                Event eventToBeAdded = new Event(taskDescription, fromAndTo[0].split("from ")[1], fromAndTo[1]);
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
