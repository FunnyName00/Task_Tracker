

public class Main {

    public static void main(String[] args){

        FileGestion.createFile();
        taskList list = new taskList("task.json");
        list.list();

    }

}
