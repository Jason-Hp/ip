import exceptions.EmptyException;
import exceptions.RandomException;
import filehandler.FileWriter;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import filehandler.FileReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Mike {

    public static final int MAX_ITEMS = 100;
    public static final String SPACES = "     ";
    public static final int TODO_AND_SPACE = 5;
    public static final int DEADLINE_AND_SPACE = 9;
    public static final int EVENT_AND_SPACE = 6;

    enum Check {MARK, UNMARK};

    public static final String LOGO = ".------..------..------..------.\n" +
            "|M.--. ||I.--. ||K.--. ||E.--. |\n" +
            "| (\\/) || (\\/) || :/\\: || (\\/) |\n" +
            "| :\\/: || :\\/: || :\\/: || :\\/: |\n" +
            "| '--'M|| '--'I|| '--'K|| '--'E|\n" +
            "`------'`------'`------'`------'";
    public static final String LINE_SEPARATOR = "    ______________________________"+
            "______________________________";

    public static String contains(String userInput) throws RandomException {
        if (userInput.contains("mark")){
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

    public static String itemToString(Task task){
        return
                "[" + task.getSymbol() + "]" +
                "[" + task.getStatusIcon() + "]" +
                " " + task.getDescription() + "\n";
    }

    public static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {

        try{
            list = FileReader.getTasksToBeInitialized();
            System.out.println("Initialization Success!");
        }
        catch(FileNotFoundException e){
            System.out.println("File not found!");
        }

        int numberOfItems = list.size();

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Hello from\n" + LOGO);

        System.out.println(LINE_SEPARATOR + "\n" +
                SPACES+"Hello! I'm Mike!\n" +
                SPACES+"What can I do for you?\n" +
                LINE_SEPARATOR+"\n");

        String userInput = scanner.nextLine();

            while (!userInput.equalsIgnoreCase("bye")) {

                String command = "ERROR";

                try {
                    command = contains(userInput);
                } catch (RandomException e) {
                    System.out.println(LINE_SEPARATOR);
                    System.out.println(SPACES+"Invalid command. Try again.");
                    System.out.println(LINE_SEPARATOR+"\n");
                }

                switch (command) {
                case "todo":
                    try {
                        FileWriter.appendToFile(todoParser(userInput));
                        list.add(todoParser(userInput));
                        break;
                    } catch (EmptyException e) {
                        System.out.println(LINE_SEPARATOR);
                        System.out.println(SPACES+"Description is empty. Try again.");
                        System.out.println(LINE_SEPARATOR+"\n");
                        userInput = scanner.nextLine();
                        continue;
                    } catch (IOException e){
                        System.out.println(LINE_SEPARATOR);
                        System.out.println(SPACES+"IO Error. Try again.");
                        System.out.println(LINE_SEPARATOR+"\n");
                        userInput = scanner.nextLine();
                        continue;
                    }


                case "deadline":
                    try {
                        FileWriter.appendToFile(todoParser(userInput));
                        list.add(deadlineParser(userInput));
                        break;
                    } catch (EmptyException e) {
                        System.out.println(LINE_SEPARATOR);
                        System.out.println(SPACES+"Description is empty. Try again.");
                        System.out.println(LINE_SEPARATOR+"\n");
                        userInput = scanner.nextLine();
                        continue;
                    } catch (IOException e){
                        System.out.println(LINE_SEPARATOR);
                        System.out.println(SPACES+"IO Error. Try again.");
                        System.out.println(LINE_SEPARATOR+"\n");
                        userInput = scanner.nextLine();
                        continue;
                    }
                case "event":
                    try {
                        FileWriter.appendToFile(todoParser(userInput));
                        list.add(eventParser(userInput));
                        break;
                    } catch (EmptyException e) {
                        System.out.println(LINE_SEPARATOR);
                        System.out.println(SPACES+"Description is empty. Try again.");
                        System.out.println(LINE_SEPARATOR+"\n");
                        userInput = scanner.nextLine();
                        continue;
                    } catch (IOException e){
                        System.out.println(LINE_SEPARATOR);
                        System.out.println(SPACES+"IO Error. Try again.");
                        System.out.println(LINE_SEPARATOR+"\n");
                        userInput = scanner.nextLine();
                        continue;
                    }
                case "list":
                    System.out.println(LINE_SEPARATOR+"\n" +
                            SPACES+"Here are the tasks in your list:");

                    for (int i = 0; i < list.size(); i++) {
                        System.out.print(SPACES+(i + 1) + "." + itemToString(list.get(i)));
                    }

                    System.out.println(LINE_SEPARATOR+"\n");

                    userInput = scanner.nextLine();
                    continue;
                case "mark":
                    int itemToMark = Integer.parseInt(userInput.split(" ")[1]) - 1;

                    markOrUnmark(itemToMark, numberOfItems, list, Check.MARK);

                    userInput = scanner.nextLine();
                    continue;
                case "unmark":
                    int itemToUnmark = Integer.parseInt(userInput.split(" ")[1]) - 1;

                    markOrUnmark(itemToUnmark, numberOfItems, list, Check.UNMARK);

                    userInput = scanner.nextLine();
                    continue;
                case "delete":
                    int itemToDelete = Integer.parseInt(userInput.split(" ")[1]) - 1;

                    deleteTask(itemToDelete);

                    userInput = scanner.nextLine();
                    continue;
                case "ERROR":
                    userInput = scanner.nextLine();
                    continue;
                }

                System.out.println(LINE_SEPARATOR+"\n" +
                        SPACES+"Got it. I've added this task:\n" +
                        SPACES+
                        itemToString(list.get(list.size()-1))+
                        SPACES + "Now you have "+list.size()+" task in the list."+"\n"+
                        LINE_SEPARATOR+"\n");

                numberOfItems++;
                userInput = scanner.nextLine();
            }


        System.out.println(LINE_SEPARATOR+"\n" +
                SPACES +"Bye. Hope to see you again soon!\n" +
                LINE_SEPARATOR+"\n");

    }

    private static void deleteTask(int itemToDelete) {
        if (itemToDelete < 0 || itemToDelete >= list.size()) {
            outOfBounds();
            return;
        }
        Task taskToDelete = list.get(itemToDelete);
        list.remove(itemToDelete);
        System.out.println(LINE_SEPARATOR+"\n" +
                "     OK, I've removed this task:");
        System.out.print(SPACES + itemToString(taskToDelete));
        System.out.println(SPACES + "Now you have "+list.size()+" task in the list.");
        System.out.println(LINE_SEPARATOR+"\n");
    }

    private static void outOfBounds() {
        System.out.print(LINE_SEPARATOR + "\n");

        System.out.println(SPACES + "Out of bounds! Try again!");

        System.out.println(LINE_SEPARATOR + "\n");
    }

    private static void markOrUnmark(int itemToMark, int numberOfItems, ArrayList<Task> list, Check check) {
        if (itemToMark < 0 || itemToMark >= numberOfItems) {

            outOfBounds();

        } else {
            if (check == Check.MARK) {
                list.get(itemToMark).markAsDone();
                System.out.println(LINE_SEPARATOR+"\n" +
                        "     Nice! I've marked this task as done:");
            }
            else{
                list.get(itemToMark).unmarkAsDone();
                System.out.println(LINE_SEPARATOR+"\n" +
                        "     OK, I've marked this task as not done yet:");
            }

            System.out.print(SPACES + itemToString(list.get(itemToMark)));

            System.out.println(LINE_SEPARATOR+"\n");
        }
    }
}
