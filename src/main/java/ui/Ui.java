package ui;

import exceptions.RandomException;

import task.Task;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents the ui that the user interacts with. A <code>Ui</code> object handles all the interaction
 * between program and user. It handles user's input and also display to the CLI for the user to see/use.
 */
public class Ui {

    public static final String LOGO = ".------..------..------..------.\n" +
            "|M.--. ||I.--. ||K.--. ||E.--. |\n" +
            "| (\\/) || (\\/) || :/\\: || (\\/) |\n" +
            "| :\\/: || :\\/: || :\\/: || :\\/: |\n" +
            "| '--'M|| '--'I|| '--'K|| '--'E|\n" +
            "`------'`------'`------'`------'";

    public static final String LINE_SEPARATOR = "    ______________________________"+
            "______________________________";

    public static final String SPACES = "     ";

    private Scanner scanner;
    private String userInput;
    private String command;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the user's input using a scanner and parsing the input to update the current
     * command. Returns the user input as a String.
     *
     * @return User's Input.
     * @throws RandomException If command is not legitimate.
     */
    public String readUserInput() throws RandomException {
        userInput = scanner.nextLine();
        command = Parser.contains(userInput);
        return userInput;
    }

    /**
     * Returns command of the current user's input.
     *
     * @return Current Command.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Format task into a suitable and printable string format. Return said string.
     *
     * @param task Task To Be Formatted.
     * @return String Formatted Task.
     */
    public String itemToString(Task task){
        return  "[" + task.getSymbol() + "]" +
                "[" + task.getStatusIcon() + "]" +
                " " + task.getDescription() + "\n";
    }

    /**
     * Prints the start of the program.
     */
    public void showStart(){
        System.out.println("Hello from\n" + LOGO);

        System.out.println(LINE_SEPARATOR + "\n" +
                SPACES+"Hello! I'm Mike!\n" +
                SPACES+"What can I do for you?\n" +
                LINE_SEPARATOR+"\n");
    }

    /**
     * Prints the end of the program.
     */
    public void showEnd(){
        System.out.println(LINE_SEPARATOR+"\n" +
                SPACES +"Bye. Hope to see you again soon!\n" +
                LINE_SEPARATOR+"\n");
    }

    /**
     * Prints error message in a suitable format.
     *
     * @param error Error Message
     */
    public void showError(String error){
        System.out.print(LINE_SEPARATOR + "\n");

        System.out.println(SPACES + error);

        System.out.println(LINE_SEPARATOR + "\n");
    }

    /**
     * Prints Out-Of-Bounds error message
     */
    public void showOutOfBounds() {
        System.out.print(LINE_SEPARATOR + "\n");

        System.out.println(SPACES + "Out of bounds! Try again!");

        System.out.println(LINE_SEPARATOR + "\n");
    }

    /**
     * Prints Random Error message.
     */
    public void showRandomException(){
        System.out.println(LINE_SEPARATOR);
        System.out.println(SPACES+"Invalid command. Try again.");
        System.out.println(LINE_SEPARATOR+"\n");
    }

    /**
     * Prints Empty Error message.
     */
    public void showEmptyException(){
        System.out.println(LINE_SEPARATOR);
        System.out.println(SPACES+"Description is empty. Try again.");
        System.out.println(LINE_SEPARATOR+"\n");
    }

    /**
     * Prints IO Error message.
     */
    public void showIOException(){
        System.out.println(LINE_SEPARATOR);
        System.out.println(SPACES+"IO Error. Try again.");
        System.out.println(LINE_SEPARATOR+"\n");
    }

    /**
     * Prints the current list in a suitable format.
     *
     * @param list List Of Tasks.
     */
    public void showList(ArrayList<Task> list){
        System.out.println(LINE_SEPARATOR+"\n" +
                SPACES+"Here are the tasks in your list:");

        for (int i = 0; i < list.size(); i++) {
            System.out.print(SPACES+(i + 1) + "." + itemToString(list.get(i)));
        }

        System.out.println(LINE_SEPARATOR+"\n");
    }

    /**
     * Prints the recently added task in a suitable format.
     *
     * @param list List Of Task.
     */
    public void showAddTask(ArrayList<Task> list){
        System.out.println(LINE_SEPARATOR+"\n" +
                SPACES+"Got it. I've added this task:\n" +
                SPACES+
                itemToString(list.get(list.size()-1))+
                SPACES + "Now you have "+list.size()+" task in the list."+"\n"+
                LINE_SEPARATOR+"\n");

    }

    public void showMarkTask(ArrayList<Task> list, int itemToMark){
        System.out.println(LINE_SEPARATOR+"\n" +
                "     Nice! I've marked this task as done:");
        System.out.print(SPACES + itemToString(list.get(itemToMark)));
        System.out.println(LINE_SEPARATOR+"\n");
    }

    public void showUnmarkTask(ArrayList<Task> list, int itemToMark){
        System.out.println(LINE_SEPARATOR+"\n" +
                "     OK, I've unmarked this task as done:");
        System.out.print(SPACES + itemToString(list.get(itemToMark)));
        System.out.println(LINE_SEPARATOR+"\n");
    }

    public void showDeleteTask(ArrayList<Task> list, Task deletedTask){
        System.out.println(LINE_SEPARATOR+"\n" +
                "     OK, I've removed this task:");
        System.out.print(SPACES + itemToString(deletedTask));
        System.out.println(SPACES + "Now you have "+list.size()+" task in the list.");
        System.out.println(LINE_SEPARATOR+"\n");
    }

}
