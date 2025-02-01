import java.util.Scanner;

public class Mike {

    public static final int MAX_ITEMS = 100;
    public static final String SPACES = "     ";

    enum Check {MARK, UNMARK};

    public static final String LOGO = ".------..------..------..------.\n" +
            "|M.--. ||I.--. ||K.--. ||E.--. |\n" +
            "| (\\/) || (\\/) || :/\\: || (\\/) |\n" +
            "| :\\/: || :\\/: || :\\/: || :\\/: |\n" +
            "| '--'M|| '--'I|| '--'K|| '--'E|\n" +
            "`------'`------'`------'`------'";
    public static final String LINE_SEPARATOR = "    ______________________________"+
            "______________________________";

    public static String contains(String userInput){
        if (userInput.contains("mark")){
            return "mark";
        } else if (userInput.contains("list")) {
            return "list";
        } else if (userInput.contains("bye")) {
            return "bye";
        } else if (userInput.contains("todo")) {
            return "todo";
        } else if (userInput.contains("deadline")) {
            return "deadline";
        } else if (userInput.contains("event")) {
            return "event";
        }
        return "ERROR";
    }

    public static Todo todoParser(String userInput){
        String todo = userInput.substring(5);
        return new Todo(todo);
    }

    public static Deadline deadlineParser(String userInput){
        String deadline = userInput.substring(9);
        String[] deadlineSplit = deadline.split(" /by ");
        return new Deadline(deadlineSplit[0], deadlineSplit[1]);
    }

    public static Event eventParser(String userInput){
        String event = userInput.substring(6);
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

    public static void main(String[] args) {
        Task[] list = new Task[MAX_ITEMS];
        int numberOfItems = 0;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Hello from\n" + LOGO);

        System.out.println(LINE_SEPARATOR + "\n" +
                SPACES+"Hello! I'm Mike!\n" +
                SPACES+"What can I do for you?\n" +
                LINE_SEPARATOR+"\n");

        String userInput = scanner.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {

            String command = contains(userInput);

            switch (command) {
            case "todo":
                list[numberOfItems] = todoParser(userInput);
                break;
            case "deadline":
                list[numberOfItems] = deadlineParser(userInput);
                break;
            case "event":
                list[numberOfItems] = eventParser(userInput);
                break;
            case "list":
                System.out.println(LINE_SEPARATOR+"\n" +
                        SPACES+"Here are the tasks in your list:");
                
                for (int i = 0; i < numberOfItems; i++) {
                    System.out.print(SPACES+(i + 1) + "." + itemToString(list[i]));
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
            case "ERROR":
                System.out.println("Invalid command. Try again.");
                userInput = scanner.nextLine();
                continue;
            }

            System.out.println(LINE_SEPARATOR+"\n" +
                    SPACES+"Got it. I've added this task:\n" +
                    SPACES+
                    itemToString(list[numberOfItems])+
                    SPACES + "Now you have "+(numberOfItems+1)+" task in the list."+"\n"+
                    LINE_SEPARATOR+"\n");
            numberOfItems++;
            userInput = scanner.nextLine();

        }

        System.out.println(LINE_SEPARATOR+"\n" +
                SPACES +"Bye. Hope to see you again soon!\n" +
                LINE_SEPARATOR+"\n");

    }

    private static void markOrUnmark(int itemToMark, int numberOfItems, Task[] list, Check check) {
        if (itemToMark < 0 || itemToMark >= numberOfItems) {

            System.out.print(LINE_SEPARATOR + "\n");

            System.out.println(SPACES + "Out of bounds! Try again!");

            System.out.println(LINE_SEPARATOR + "\n");

        } else {
            if (check == Check.MARK) {
                list[itemToMark].markAsDone();
                System.out.println(LINE_SEPARATOR+"\n" +
                        "     Nice! I've marked this task as done:");
            }
            else{
                list[itemToMark].unmarkAsDone();
                System.out.println(LINE_SEPARATOR+"\n" +
                        "     OK, I've marked this task as not done yet:");
            }

            System.out.print(SPACES + itemToString(list[itemToMark]));

            System.out.println(LINE_SEPARATOR+"\n");
        }
    }
}
