import exceptions.EmptyException;
import exceptions.OutOfBounds;
import exceptions.RandomException;

import filehandler.FileWriterNew;
import filehandler.FileReader;

import task.TaskList;

import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Mike {
    private Ui ui;
    private TaskList list;
    private FileReader fileReader;
    private FileWriterNew fileWriter;

    public Mike(){
        ui = new Ui();
        fileReader = new FileReader("./data/mike.txt");
        fileWriter = new FileWriterNew("./data/mike.txt");
        try{
            list = new TaskList(fileReader.getTasksToBeInitialized());
        } catch (FileNotFoundException e){
            ui.showError(e.getMessage());
            list = new TaskList();
        }
    }

    public void run(){
        assert fileReader != null : "FileReader should not be null";
        assert fileWriter != null : "FileWriter should not be null";
        assert list != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";

        ui.showStart();

        boolean isDone = false;
        String userInput;
        String command;

        while(!isDone){
            try {
                userInput = ui.readUserInput();
                command = ui.getCommand();
            } catch (RandomException e) {
                ui.showRandomException();
                continue;
            }

            switch(command){
            case "bye":
                isDone = true;
                break;
            case "todo":
            case "deadline":
            case "event":
                try{
                    fileWriter.appendToFile(list.addTask(command,userInput));
                    ui.showAddTask(list.getList());
                } catch (EmptyException e){
                    ui.showEmptyException();
                } catch (IOException e) {
                    ui.showIOException();
                } finally {
                    break;
                }
            case "list":
                ui.showList(list.getList());
                break;
            case "mark":
                try{
                    int index = list.markTask(userInput);
                    ui.showMarkTask(list.getList(),index);
                    fileWriter.markTask(index);
                } catch (OutOfBounds e) {
                    ui.showOutOfBounds();
                } catch (IOException e) {
                    ui.showIOException();
                } finally {
                    break;
                }
            case "unmark":
                try{
                    int index = list.UnmarkTask(userInput);
                    ui.showUnmarkTask(list.getList(),index);
                    fileWriter.unmarkTask(index);
                } catch (OutOfBounds e) {
                    ui.showOutOfBounds();
                } catch (IOException e) {
                    ui.showIOException();
                } finally {
                    break;
                }
            case "delete":
                try{
                    ui.showDeleteTask(list.getList(), list.deleteTask(userInput));
                    fileWriter.deleteTask(userInput);
                } catch (OutOfBounds e) {
                    ui.showOutOfBounds();
                } catch (IOException e) {
                    ui.showIOException();
                }
                finally {
                    break;
                }

            }
        }
        ui.showEnd();
    }

    public static void main(String[] args){
        new Mike().run();
    }

}
