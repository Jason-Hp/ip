package ui;

import exceptions.EmptyException;
import exceptions.RandomException;
import task.Deadline;
import task.Event;
import task.Todo;

/**
 * Represents a parser. This class is to be used without instantiating, as all of its methods and attributes
 * are static. This class parses user's input and returns suitable commands/responses.
 */
public class Parser {

    public static final int TODO_AND_SPACE = 5;
    public static final int DEADLINE_AND_SPACE = 9;
    public static final int EVENT_AND_SPACE = 6;

    /**
     * Parses user's input and returns one of the follow commands or throws a Random error.
     *
     * @param userInput User's Input.
     * @return Command.
     * @throws RandomException If User's Input is invalid.
     */
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

    /**
     * Parses user's input that contains the command "todo" and returns a Todo object.
     *
     * @param userInput User's Input.
     * @return Todo Object.
     * @throws EmptyException If todo task is missing/empty.
     */
    public static Todo todoParser(String userInput) throws EmptyException {
        String todo = userInput.substring(TODO_AND_SPACE);
        if (todo.isEmpty()){
            throw new EmptyException();
        }
        return new Todo(todo);
    }

    /**
     * Parses user's input that contains the command "deadline" and returns a Deadline object.
     *
     * @param userInput User's Input.
     * @return Deadline Object.
     * @throws EmptyException If deadline task is missing/empty.
     */
    public static Deadline deadlineParser(String userInput) throws EmptyException{
        String deadline = userInput.substring(DEADLINE_AND_SPACE);
        if (deadline.isEmpty()){
            throw new EmptyException();
        }
        String[] deadlineSplit = deadline.split(" /by ");
        return new Deadline(deadlineSplit[0], deadlineSplit[1]);
    }

    /**
     * Parses user's input that contains the command "event" and returns an Event object.
     *
     * @param userInput User's Input.
     * @return Event Object.
     * @throws EmptyException If event task is missing/empty.
     */
    public static Event eventParser(String userInput) throws EmptyException{
        String event = userInput.substring(EVENT_AND_SPACE);
        if (event.isEmpty()){
            throw new EmptyException();
        }
        String[] eventSplit = event.split(" /from ");
        String[] fromAndToSplit = eventSplit[1].split(" /to ");
        return new Event(eventSplit[0], fromAndToSplit[0], fromAndToSplit[1]);
    }

    /**
     * Returns index from user's input.
     *
     * @param userInput User's Input.
     * @return Index Of Task.
     */
    public static int indexOfItem(String userInput){
        return Integer.parseInt(userInput.split(" ")[1]) - 1;
    }

    /**
     * Returns task to be found.
     *
     * @param userInput User's Input
     * @return Task To Be Found.
     * @throws IndexOutOfBoundsException If Task To Be Found Is Missing/Empty.
     */
    public static String findParser(String userInput) throws IndexOutOfBoundsException{
        return userInput.split(" ")[1];
    }

}
