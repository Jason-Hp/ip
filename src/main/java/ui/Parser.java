package ui;

import exceptions.EmptyException;
import exceptions.RandomException;
import task.Deadline;
import task.Event;
import task.Todo;

public class Parser {

    public static final int TODO_AND_SPACE = 5;
    public static final int DEADLINE_AND_SPACE = 9;
    public static final int EVENT_AND_SPACE = 6;

    public static String contains(String userInput) throws RandomException {
        if (userInput.contains("unmark")){
            return "unmark";
        } else if (userInput.contains("mark")) {
            return "mark";
        } else if (userInput.contains("list")) {
            return "list";
        } else if (userInput.contains("todo")) {
            return "todo";
        } else if (userInput.contains("deadline")) {
            return "deadline";
        } else if (userInput.contains("event")) {
            return "event";
        } else if (userInput.contains("delete")) {
            return "delete";
        } else if (userInput.equalsIgnoreCase("bye")) {
            return "bye";
        } else if (userInput.contains("find")) {
            return "find";
        }
        throw new RandomException();
    }

    public static Todo todoParser(String userInput) throws EmptyException {
        String todo = userInput.substring(TODO_AND_SPACE);
        if (todo.isEmpty()){
            throw new EmptyException();
        }
        return new Todo(todo);
    }

    public static Deadline deadlineParser(String userInput) throws EmptyException{
        String deadline = userInput.substring(DEADLINE_AND_SPACE);
        if (deadline.isEmpty()){
            throw new EmptyException();
        }
        String[] deadlineSplit = deadline.split(" /by ");
        return new Deadline(deadlineSplit[0], deadlineSplit[1]);
    }

    public static Event eventParser(String userInput) throws EmptyException{
        String event = userInput.substring(EVENT_AND_SPACE);
        if (event.isEmpty()){
            throw new EmptyException();
        }
        String[] eventSplit = event.split(" /from ");
        String[] fromAndToSplit = eventSplit[1].split(" /to ");
        return new Event(eventSplit[0], fromAndToSplit[0], fromAndToSplit[1]);
    }

    public static int indexOfItem(String userInput){
        return Integer.parseInt(userInput.split(" ")[1]) - 1;
    }

    public static String findParser(String userInput) throws IndexOutOfBoundsException{
        return userInput.split(" ")[1];
    }

}
