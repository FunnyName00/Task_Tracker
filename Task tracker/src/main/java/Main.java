import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        taskList list = new taskList("task.json");
        System.out.println("What can I do for you ? Type help to print the commands");
        Scanner s = new Scanner(System.in);

        while (true){
            String command = s.nextLine();
            boolean quit = list.handleCommand(command);
            if (quit) {
                System.out.println("Bye!");
                break;
            }
        }

    }

}
