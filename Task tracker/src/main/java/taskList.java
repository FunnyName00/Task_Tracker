import java.util.ArrayList;
import java.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class taskList {
    public String filename = "task.json";
    ArrayList<Task> list = new ArrayList<>();

    public taskList(String filename) {
        this.filename = filename;
        this.list = new ArrayList<>();
        loadFromJSON(filename); // charge automatiquement le fichier
    }
    public void add(String task){
        /*
        INPUT : Str task
        RETURN : /
        Add task to list, add 0 to doneList and add date to createdDateList & updatedDateList
        */

        Task newTask = new Task(task, 0, LocalDate.now(), LocalDate.now());
        list.add(newTask);

        Integer index = list.size() - 1;
        System.out.println("Task successfully added (ID: " + index +")");
        saveToJSON(filename);
    }

    public void update(Integer index, String task){
        /*
        INPUT : Integer index, String task
        RETURN : /
        Modify task in list at index and change updatedDateList[index] to current date
        */

        if (index >= list.size() || index < 0) System.out.println("Please use a valid Task index");

        else {
            Task newTask = new Task(task,
                    list.get(index).status,
                    list.get(index).created,
                    LocalDate.now()
            );
            list.set(index, newTask);
            System.out.println("Task successfully updated (ID: " + index + ")");
            saveToJSON(filename);
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
            System.out.println("Task successfully deleted (ID: " + index +")");
            saveToJSON(filename);
        }
    }

    public void changeStatus(Integer index, Integer status){
        if (status < 0 || status > 2) System.out.println("Enter valid status");
        else {
            Task newTask = new Task(list.get(index).name, status, list.get(index).created, list.get(index).updated);
            list.set(index, newTask);
            System.out.println("Task status successfully changed (ID: " + index + ")");
            saveToJSON(filename);
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

    public void list(Integer status){
        /*
        INPUT : Integer status
        RETURN : /
        Print every tasks with a certain status in the list with all informations
        */
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).status == status) System.out.println(createStringTask(i));
        }
    }

    public String createStringTask(Integer index){
        /*
        INPUT : Integer index
        RETURN : /
        Create a string describing the task at index
        */
        String done = "";
        switch (list.get(index).status){
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

        String string = (index + "| " + list.get(index).name
                + " | status : " + done
                + " | created on : " + list.get(index).created
                + " | updated on : " + list.get(index).updated
        );

        return string;
    }


    public void saveToJSON(String filename) {
        JSONArray array = new JSONArray();

        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);

            JSONObject obj = new JSONObject();
            obj.put("name", t.name);
            obj.put("status", t.status);
            obj.put("created", t.created.toString());
            obj.put("updated", t.updated.toString());

            array.put(obj);
        }

        try (FileWriter file = new FileWriter(filename)) {
            file.write(array.toString(4));  // indentation 4 spaces
            System.out.println("Tasks saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromJSON(String filename) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));

            JSONArray array = new JSONArray(content);

            list.clear(); // important !

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                Task t = new Task(
                        obj.getString("name"),
                        obj.getInt("status"),
                        LocalDate.parse(obj.getString("created")),
                        LocalDate.parse(obj.getString("updated"))
                );

                list.add(t);
            }

            System.out.println("Tasks loaded from " + filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
