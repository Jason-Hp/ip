package task;

import exceptions.EmptyException;
import exceptions.OutOfBounds;
import filehandler.FileWriterNew;
import ui.Parser;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    private int numberOfTasks;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.numberOfTasks = list.size();
    }

    public TaskList() {
        this.list = new ArrayList<>();
        this.numberOfTasks = 0;
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void addTodo(String userInput) throws EmptyException {
        list.add(Parser.todoParser(userInput));
    }

    public void addDeadline(String userInput) throws EmptyException {
        list.add(Parser.deadlineParser(userInput));
    }

    public void addEvent(String userInput) throws EmptyException {
        list.add(Parser.eventParser(userInput));
    }

    public void addTask(String command, String userInput) throws EmptyException {
        switch (command) {
            case "todo":
                addTodo(userInput);
                break;
            case "deadline":
                addDeadline(userInput);
                break;
            case "event":
                addEvent(userInput);
                break;
        }
        numberOfTasks++;
    }

    public Task deleteTask(ArrayList<Task> list, int itemToDelete) throws OutOfBounds {
        if (itemToDelete < 0 || itemToDelete >= list.size()) {
            throw new OutOfBounds();
        }

        list.remove(itemToDelete);
        return list.get(itemToDelete);

    }

    private static void markTask(int itemToMark, ArrayList<Task> list) throws OutOfBounds {
        if (itemToMark < 0 || itemToMark >= list.size()) {
            throw new OutOfBounds();
        }
        list.get(itemToMark).markAsDone();
    }

    private static void UnmarkTask(int itemToMark, ArrayList<Task> list) throws OutOfBounds {
        if (itemToMark < 0 || itemToMark >= list.size()) {
            throw new OutOfBounds();
        }
        list.get(itemToMark).unmarkAsDone();
    }
}
