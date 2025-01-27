import java.util.Scanner;

public class Mike {
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

        System.out.println("    _____________________________"+
                "_______________________________\n" +
                "     Hello! I'm Mike!\n" +
                "     What can I do for you?\n" +
                "    _________________________________"+
                "___________________________\n");

        String userInput = scanner.nextLine();
        while(!userInput.equalsIgnoreCase("bye")){

            if(userInput.contains("mark")){
                int itemToMark = Integer.parseInt(userInput.split(" ")[1])-1;

                if (itemToMark<0 || itemToMark>=numberOfItems){

                    System.out.println("    _________________________________"+
                            "___________________________");

                    System.out.println("     Mark inside the list! Try again!");

                    System.out.println("    _________________________________"+
                            "___________________________\n");

                    userInput = scanner.nextLine();
                    continue;
                }
                list[itemToMark].markAsDone();
                System.out.println("    _________________________________"+
                        "___________________________\n"+
                        "     Nice! I've marked this task as done:");

                System.out.println("       " + "[" + list[itemToMark].getStatusIcon() +"] " + list[itemToMark].description);

                System.out.println("    _________________________________"+
                        "___________________________\n");

                userInput = scanner.nextLine();
                continue;
            }


            if(userInput.equalsIgnoreCase("list")){
                System.out.println("    _________________________________"+
                        "___________________________\n"+
                        "     Here are the tasks in your list:");
                for(int i = 0; i < numberOfItems; i++){
                    System.out.println("     "+(i + 1) + "." + "[" + list[i].getStatusIcon() + "] " + list[i].description);
                }
                System.out.println("    _________________________________"+
                        "___________________________\n");
                userInput = scanner.nextLine();
                continue;
            }

            System.out.println("    _________________________________"+
                    "___________________________\n"+
                    "     added: "+
                    userInput+
                    "\n"+
                    "    _________________________________"+
                    "___________________________\n");


            Task t = new Task(userInput);
            list[numberOfItems] = t;
            numberOfItems++;
            userInput = scanner.nextLine();
        }

        System.out.println("    _________________________________"+
                "___________________________\n"+
                "     Bye. Hope to see you again soon!\n" +
                "    _________________________________"+
                "___________________________\n");

    }
}
