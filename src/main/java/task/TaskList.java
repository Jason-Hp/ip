package task;

import exceptions.EmptyException;
import exceptions.OutOfBounds;

import ui.Parser;

import java.util.ArrayList;

/**
 * Represents a task list. A <code>TaskList</code> object is an object that
 * handles the list of tasks, by adding, updating, getting/reading, or deleting tasks
 * from the list.
 */
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

    /**
     * Returns the current list of tasks.
     *
     * @return Task List.
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Adds a todo into the list and return said todo as well.
     *
     * @param userInput User's input.
     * @return Added Task.
     * @throws EmptyException If there is no todo .
     */
    public Task addTodo(String userInput) throws EmptyException {
        Task task = Parser.todoParser(userInput);
        list.add(task);
        return task;
    }

    /**
     * Adds a deadline into the list and return said deadline as well.
     *
     * @param userInput User's input.
     * @return Added Deadline.
     * @throws EmptyException If there is no deadline.
     */
    public Task addDeadline(String userInput) throws EmptyException {
        Task task = Parser.deadlineParser(userInput);
        list.add(task);
        return task;
    }

    /**
     * Adds an event into the list and return said event as well.
     *
     * @param userInput User's input.
     * @return Added event.
     * @throws EmptyException If there is no event.
     */
    public Task addEvent(String userInput) throws EmptyException {
        Task task = Parser.eventParser(userInput);
        list.add(task);
        return task;
    }

    /**
     * Adds a task into the list and return said task as well.
     *
     * @param userInput User's input
     * @return Added Task
     * @throws EmptyException If there is no task
     */
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

    public ArrayList<Task> findTasks(String userInput) throws IndexOutOfBoundsException {
        String taskToBeFound = Parser.findParser(userInput);
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : list) {
            if (task.getDescription().contains(taskToBeFound)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
