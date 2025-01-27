import java.util.Scanner;

public class Mike {
    public static void main(String[] args) {
        String[] list = new String[100];
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

            if(userInput.equalsIgnoreCase("list")){
                System.out.println("    _________________________________"+
                        "___________________________");
                for(int i = 0; i < numberOfItems; i++){
                    System.out.println("     "+(i + 1) + ". " + list[i]);
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

            list[numberOfItems] = userInput;
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
