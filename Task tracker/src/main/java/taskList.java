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

        if (index >= list.size() || index < 0) System.out.println("Please use a valid Task index");

        else {
            list.set(index, task);
            LocalDate date = LocalDate.now();
            updatedDateList.set(index, date);
            System.out.println("Task successfully updated (ID: " + index + ")");
        }
    }

    public void delete(Integer index){
        /*
        INPUT : Integer index
        RETURN : /
        Delete task in every lists at index
        */

        if (index >= list.size() || index < 0) System.out.println("Please use a valid Task index");

        else {
            list.remove(index);
            doneList.remove(index);
            createdDateList.remove(index);
            updatedDateList.remove(index);

            System.out.println("Task successfully deleted (ID: " + index +")");
        }
    }

    public void list(){
        /*
        INPUT : /
        RETURN : /
        Print every tasks in the list with all informations
        */
        for (int i = 0; i < list.size(); i++){
            System.out.println(createStringTask(i));
        }
    }


    public String createStringTask(Integer index){
        /*
        INPUT : Integer index
        RETURN : /
        Create a string describing the task at index
        */
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
