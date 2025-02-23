package filehandler;

import task.Task;
import task.Deadline;
import task.Event;
import ui.Parser;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class FileWriterNew {
    private String filePath;

    public FileWriterNew(String filePath) {
        this.filePath = (new File(filePath)).getAbsolutePath();
    }

    public void appendToFile(Task task) throws IOException {
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

    public void deleteTask(String userInput) throws IOException {
        int index = Parser.indexOfItem(userInput);
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String newList = "";
        int currentIndex = 0;
        while (s.hasNextLine()) {
            String taskInformation = s.nextLine();
            if(currentIndex != index){
                newList += taskInformation + System.lineSeparator();
            }
            currentIndex++;
        }
        FileWriter fw = new FileWriter(filePath);
        fw.write(newList);
        fw.close();
    }

    public void markTask(int index) throws IOException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String newList = "";
        int currentIndex = 0;
        while (s.hasNextLine()) {
            String taskInformation = s.nextLine();
            if(currentIndex == index){
                taskInformation = taskInformation.replaceFirst("0","1");
                newList += taskInformation + System.lineSeparator();
            }else{
                newList += taskInformation + System.lineSeparator();
            }
            currentIndex++;
        }
        FileWriter fw = new FileWriter(filePath);
        fw.write(newList);
        fw.close();
    }

    public void unmarkTask(int index) throws IOException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String newList = "";
        int currentIndex = 0;
        while (s.hasNextLine()) {
            String taskInformation = s.nextLine();
            if(currentIndex == index){
                taskInformation = taskInformation.replaceFirst("1","0");
                newList += taskInformation + System.lineSeparator();
            }else{
                newList += taskInformation + System.lineSeparator();
            }
            currentIndex++;
        }
        FileWriter fw = new FileWriter(filePath);
        fw.write(newList);
        fw.close();
    }
}
