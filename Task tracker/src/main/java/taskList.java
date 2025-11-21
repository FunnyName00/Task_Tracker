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

        System.out.println(list +" " + doneList + " " + createdDateList + " " + updatedDateList);
    }

}
