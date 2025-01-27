import java.util.Scanner;

public class Mike {
    public static void main(String[] args) {

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
            System.out.println("    _________________________________"+
                    "___________________________\n"+
                    "     "+
                    userInput+
                    "\n"+
                    "    _________________________________"+
                    "___________________________\n");
            userInput = scanner.nextLine();
        }

        System.out.println("    _________________________________"+
                "___________________________\n"+
                "     Bye. Hope to see you again soon!\n" +
                "    _________________________________"+
                "___________________________\n");

    }
}
