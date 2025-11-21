import java.util.ArrayList;
import java.time.LocalDate;

public class taskList {
    ArrayList<String> list = new ArrayList<>();
    ArrayList<Integer> doneList = new ArrayList<>();
    ArrayList<LocalDate> createdDateList = new ArrayList<>();
    ArrayList<LocalDate> updatedDateList = new ArrayList<>();

    public void add(String task){
        /*
        INPUT : Str task
        RETURN : /
        Add task to list, add 0 to doneList and add date to createdDateList & updatedDateList
        */

        list.add(task);
        doneList.add(0);

        LocalDate date = LocalDate.now();
        createdDateList.add(date);
        updatedDateList.add(date);
        System.out.println("Task successfully added (ID: " + list.indexOf(task) +")");
    }

    public void update(Integer index, String task){
        /*
        INPUT : Integer index, String task
        RETURN : /
        Modify task in list at index and change updatedDateList[index] to current date
        */

        list.set(index, task);
        LocalDate date = LocalDate.now();
        updatedDateList.set(index, date);
        System.out.println("Task successfully updated (ID: " + index +")");
    }

    public String createStringTask(Integer index){

        String done = "";
        switch (doneList.get(index)){
            case (0):
                done = "todo";
                break;
            case (1):
                done = "in-progress";
                break;
            case (2):
                done = "done";
                break;
        }

        String string = (index + "| " + list.get(index)
                + " | status : " + done
                + " | created on : " + createdDateList.get(index)
                + " | updated on : " + updatedDateList.get(index));

        return string;
    }


}
