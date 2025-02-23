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

    public Task addTodo(String userInput) throws EmptyException {
        Task task = Parser.todoParser(userInput);
        list.add(task);
        return task;
    }

    public Task addDeadline(String userInput) throws EmptyException {
        Task task = Parser.deadlineParser(userInput);
        list.add(task);
        return task;
    }

    public Task addEvent(String userInput) throws EmptyException {
        Task task = Parser.eventParser(userInput);
        list.add(task);
        return task;
    }

    public Task addTask(String command, String userInput) throws EmptyException {
        Task task;
        switch (command) {
        case "todo":
            task = addTodo(userInput);
            break;
        case "deadline":
            task = addDeadline(userInput);
            break;
        case "event":
            task = addEvent(userInput);
            break;
        default:
            task = null;
        }
        numberOfTasks++;
        return task;
    }

    public Task deleteTask(String userInput) throws OutOfBounds {
        int itemToDelete = Parser.indexOfItem(userInput);
        if (itemToDelete < 0 || itemToDelete >= list.size()) {
            throw new OutOfBounds();
        }
        Task temp = list.get(itemToDelete);
        list.remove(itemToDelete);
        return temp;

    }

    public int markTask(String userInput) throws OutOfBounds {
        int itemToMark = Parser.indexOfItem(userInput);
        if (itemToMark < 0 || itemToMark >= list.size()) {
            throw new OutOfBounds();
        }
        list.get(itemToMark).markAsDone();
        return itemToMark;
    }

    public int UnmarkTask(String userInput) throws OutOfBounds {
        int itemToUnmark = Parser.indexOfItem(userInput);
        if (itemToUnmark < 0 || itemToUnmark >= list.size()) {
            throw new OutOfBounds();
        }
        list.get(itemToUnmark).unmarkAsDone();
        return itemToUnmark;
    }
}
