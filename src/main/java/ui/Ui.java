package ui;

import exceptions.RandomException;
import task.Task;
import java.util.Scanner;
import java.util.ArrayList;
import ui.Parser;

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

    public String readUserInput() throws RandomException {
        userInput = scanner.nextLine();
        command = Parser.contains(userInput);
        return userInput;
    }

    public String getCommand() {
        return command;
    }

    public String itemToString(Task task){
        return  "[" + task.getSymbol() + "]" +
                "[" + task.getStatusIcon() + "]" +
                " " + task.getDescription() + "\n";
    }

    public void showStart(){
        System.out.println("Hello from\n" + LOGO);

        System.out.println(LINE_SEPARATOR + "\n" +
                SPACES+"Hello! I'm Mike!\n" +
                SPACES+"What can I do for you?\n" +
                LINE_SEPARATOR+"\n");
    }

    public void showEnd(){
        System.out.println(LINE_SEPARATOR+"\n" +
                SPACES +"Bye. Hope to see you again soon!\n" +
                LINE_SEPARATOR+"\n");
    }

    public void showError(String error){
        System.out.print(LINE_SEPARATOR + "\n");

        System.out.println(SPACES + error);

        System.out.println(LINE_SEPARATOR + "\n");
    }

    public void showOutOfBounds() {
        System.out.print(LINE_SEPARATOR + "\n");

        System.out.println(SPACES + "Out of bounds! Try again!");

        System.out.println(LINE_SEPARATOR + "\n");
    }

    public void showRandomException(){
        System.out.println(LINE_SEPARATOR);
        System.out.println(SPACES+"Invalid command. Try again.");
        System.out.println(LINE_SEPARATOR+"\n");
    }

    public void showEmptyException(){
        System.out.println(LINE_SEPARATOR);
        System.out.println(SPACES+"Description is empty. Try again.");
        System.out.println(LINE_SEPARATOR+"\n");
    }

    public void showIOException(){
        System.out.println(LINE_SEPARATOR);
        System.out.println(SPACES+"IO Error. Try again.");
        System.out.println(LINE_SEPARATOR+"\n");
    }

    public void showList(ArrayList<Task> list){
        System.out.println(LINE_SEPARATOR+"\n" +
                SPACES+"Here are the tasks in your list:");

        for (int i = 0; i < list.size(); i++) {
            System.out.print(SPACES+(i + 1) + "." + itemToString(list.get(i)));
        }

        System.out.println(LINE_SEPARATOR+"\n");
    }

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

    public void showDeleteTask(ArrayList<Task> list, int itemToDelete){
        System.out.println(LINE_SEPARATOR+"\n" +
                "     OK, I've removed this task:");
        System.out.print(SPACES + itemToString(list.get(itemToDelete)));
        System.out.println(SPACES + "Now you have "+list.size()+" task in the list.");
        System.out.println(LINE_SEPARATOR+"\n");
    }

}
