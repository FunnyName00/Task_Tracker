import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        //FileGestion.createFile();
        taskList list = new taskList("task.json");

        Scanner s = new Scanner(System.in);

        while (true){
            String command = s.nextLine();
            list.handleCommand(command);
        }

    }

}
