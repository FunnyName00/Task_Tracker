

public class Main {

    public static void main(String[] args){

        taskList list = new taskList();
        list.add("Sortir le chien");
        System.out.println(list.list);
        list.update(0, "Ah non enfait oups :3");
        String str = list.createStringTask(0);
        System.out.println(str);
    }

}
