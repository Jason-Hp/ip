import java.util.Scanner;

public class Mike {

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
        String deadline = userInput.substring(8);
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
        Task[] list = new Task[100];
        int numberOfItems = 0;
        Scanner scanner = new Scanner(System.in);

        String logo = ".------..------..------..------.\n" +
                "|M.--. ||I.--. ||K.--. ||E.--. |\n" +
                "| (\\/) || (\\/) || :/\\: || (\\/) |\n" +
                "| :\\/: || :\\/: || :\\/: || :\\/: |\n" +
                "| '--'M|| '--'I|| '--'K|| '--'E|\n" +
                "`------'`------'`------'`------'";
        System.out.println("Hello from\n" + logo);

        System.out.println("    _____________________________" +
                "_______________________________\n" +
                "     Hello! I'm Mike!\n" +
                "     What can I do for you?\n" +
                "    _________________________________" +
                "___________________________\n");

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
                System.out.println("    _________________________________" +
                        "___________________________\n" +
                        "     Here are the tasks in your list:");

                for (int i = 0; i < numberOfItems; i++) {
                    System.out.print("     "+(i + 1) + "." + itemToString(list[i]));
                }

                System.out.println("    _________________________________" +
                        "___________________________\n");

                userInput = scanner.nextLine();
                continue;
            case "mark":
                int itemToMark = Integer.parseInt(userInput.split(" ")[1]) - 1;

                if (itemToMark < 0 || itemToMark >= numberOfItems) {

                    System.out.print("    _________________________________" +
                            "___________________________\n");

                    System.out.println("     "+"Out of bounds! Try again!");

                    System.out.println("    _________________________________" +
                            "___________________________\n");

                    userInput = scanner.nextLine();
                    continue;
                }
                list[itemToMark].markAsDone();
                System.out.println("    _________________________________" +
                        "___________________________\n" +
                        "     Nice! I've marked this task as done:");

                System.out.print("     "+itemToString(list[itemToMark]));

                System.out.println("    _________________________________" +
                        "___________________________\n");
                userInput = scanner.nextLine();
                continue;
            case "unmark":
                int itemToUnmark = Integer.parseInt(userInput.split(" ")[1]) - 1;

                if (itemToUnmark < 0 || itemToUnmark >= numberOfItems) {

                    System.out.println("    _________________________________" +
                            "___________________________");

                    System.out.println("     "+"Out of bounds! Try again!");

                    System.out.println("    _________________________________" +
                            "___________________________\n");

                    userInput = scanner.nextLine();
                    continue;
                }
                list[itemToUnmark].unmarkAsDone();
                System.out.println("    _________________________________" +
                        "___________________________\n" +
                        "     "+"OK, I've marked this task as not done yet:");

                System.out.println("     "+itemToString(list[itemToUnmark]));

                System.out.println("    _________________________________" +
                        "___________________________\n");

                userInput = scanner.nextLine();
                continue;
            case "ERROR":
                System.out.println("Invalid command. Try again.");
                userInput = scanner.nextLine();
                continue;
            }

            System.out.println("    _________________________________" +
                    "___________________________\n" +
                    "     "+"Got it. I've added this task:\n" +
                    itemToString(list[numberOfItems])+
                    "     " + "Now you have "+(numberOfItems+1)+" task in the list."+"\n"+
                    "    _________________________________" +
                    "___________________________\n");
            numberOfItems++;
            userInput = scanner.nextLine();

        }

        System.out.println("    _________________________________" +
                "___________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    _________________________________" +
                "___________________________\n");

    }
}
