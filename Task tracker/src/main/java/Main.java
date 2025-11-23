

public class Main {

    public static void main(String[] args){

        taskList list = new taskList("task.json");
        FileGestion.createFile();
        while (true){
            InputGestion.input(list);
        }

    }

}
