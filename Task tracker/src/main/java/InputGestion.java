import java.util.Scanner;

public class InputGestion {

    public static String input(taskList list){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("What can I do for you ?");

        String command = myObj.nextLine();  // Read user input

        commandDecoder(command, list);
        return command;// Output user input
    }

    public static void commandDecoder(String command, taskList list){

        String[] newCommand  = command.split(" ");

        switch (newCommand[0]){

            case "add": {
                String task = new String();
                for (int i = 1; i < newCommand.length; i++){
                    task += newCommand[i] + " ";
                }
                list.add(task);
                break;
            }

            case "update": {
                String task = new String();
                for (int i = 2; i < newCommand.length; i++){
                    task += newCommand[i];
                }
                Integer index = Integer.parseInt(newCommand[1]);
                list.update(index, task);
                break;
            }

            case "delete": {
                list.delete(Integer.parseInt(newCommand[1]));
                break;
            }

            case "list":{
                list.list();
                break;
            }

        }
    }

}
