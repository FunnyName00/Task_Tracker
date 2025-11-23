import java.util.ArrayList;
import java.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class taskList {

    public String filename;
    ArrayList<Task> list;

    public taskList(String filename) {
        this.filename = filename;
        this.list = new ArrayList<>();
        loadFromJSON();     // charge automatiquement le fichier
    }

    public taskList() {
        this("task.json");
    }

    public void add(String task){
        Task newTask = new Task(task, 0, LocalDate.now(), LocalDate.now());
        list.add(newTask);

        System.out.println("Task successfully added (ID: " + (list.size() - 1) +")");
        saveToJSON();
    }

    public void update(Integer index, String task){
        if (index >= list.size() || index < 0) {
            System.out.println("Please use a valid Task index");
            return;
        }

        Task old = list.get(index);
        Task newTask = new Task(task, old.status, old.created, LocalDate.now());

        list.set(index, newTask);
        System.out.println("Task updated (ID: " + index + ")");
        saveToJSON();
    }

    public void delete(Integer index){
        if (index >= list.size() || index < 0) {
            System.out.println("Please use a valid Task index");
            return;
        }

        //System.out.println("DEBUG BEFORE DELETE: " + list.size());
        list.remove(list.get(index));
        //System.out.println("DEBUG AFTER DELETE: " + list.size());

        System.out.println("Task deleted (ID: " + index + ")");
        saveToJSON();
    }

    public void changeStatus(Integer index, Integer status){
        if (status < 0 || status > 2) {
            System.out.println("Enter valid status");
            return;
        }

        Task old = list.get(index);
        Task t = new Task(old.name, status, old.created, old.updated);

        list.set(index, t);
        System.out.println("Task status changed (ID: " + index + ")");
        saveToJSON();
    }

    public void list(){
        if (list.size() == 0) System.out.println("The list is empty, try 'add [task]' to add a task to the list");
        for (int i = 0; i < list.size(); i++){
            System.out.println(createStringTask(i));
        }
    }

    public String createStringTask(Integer index){
        Task t = list.get(index);

        String done = switch(t.status) {
            case 0 -> "todo";
            case 1 -> "in-progress";
            case 2 -> "done";
            default -> "unknown";
        };

        return index + "| " + t.name
                + " | status : " + done
                + " | created : " + t.created
                + " | updated : " + t.updated;
    }

    public void saveToJSON() {
        JSONArray array = new JSONArray();

        for (Task t : list) {
            JSONObject obj = new JSONObject();
            obj.put("name", t.name);
            obj.put("status", t.status);
            obj.put("created", t.created.toString());
            obj.put("updated", t.updated.toString());
            array.put(obj);
        }

        try (FileWriter file = new FileWriter(filename)) {
            file.write(array.toString(4));
            //System.out.println("Tasks saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromJSON() {
        try {
            if (!Files.exists(Paths.get(filename))) {
                Files.write(Paths.get(filename), "[]".getBytes());
                System.out.println("Created new task file: " + filename);
            }

            String content = new String(Files.readAllBytes(Paths.get(filename)));
            JSONArray array = new JSONArray(content);

            list.clear();

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

            //System.out.println("Tasks loaded from " + filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean handleCommand(String command){

        String[] newCommand  = command.split(" ");

        switch (newCommand[0]){

            case "add": {
                String task = new String();
                for (int i = 1; i < newCommand.length; i++){
                    task += newCommand[i] + " ";
                }
                add(task);
                break;
            }

            case "update": {
                String task = new String();
                for (int i = 2; i < newCommand.length; i++){
                    task += newCommand[i];
                }
                Integer index = Integer.parseInt(newCommand[1]);
                update(index, task);
                break;
            }

            case "delete": {
                delete(Integer.parseInt(newCommand[1]));
                break;
            }

            case "list":{
                list();
                break;
            }

            case "mark-in-progress":{
                changeStatus(Integer.parseInt(newCommand[1]), 1);
                break;
            }

            case "mark-done":{
                changeStatus(Integer.parseInt(newCommand[1]), 2);
                break;
            }

            case "help":{
                System.out.println(
                        "- add [task] : add a task to the list \n" +
                        "- update [index] [new task name]: update the task at index [index] to [new task name] \n" +
                        "- delete [index] : delete task at index [index] \n" +
                        "- list : list all available tasks \n" +
                        "- mark-in-progress [index] : mark task at [index] as 'in progress' \n" +
                        "- mark-done [index] : mark task at [index] as 'done'" +
                        "- quit : quit the program");
                break;
            }

            case "quit":{
                return true;
            }

            default:{
                System.out.println("I think you have made a mistake in your command" +
                        ", try 'help' for a list of all available commands");
                break;
            }

        }
        return false;
    }
}
