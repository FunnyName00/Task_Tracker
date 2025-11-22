

public class Main {

    public static void main(String[] args){
        FileGestion.createFile();

        taskList list = new taskList();

        list.add("Sortir le chien");
        list.add("Manger des céréales");
        list.add("Faire maths discrètes");
        list.update(0, "Sortir le chat");
        System.out.println("----------------");
        list.list();
        System.out.println("----------------");
        list.changeStatus(0, 0);
        list.changeStatus(1, 1);

        FileGestion.readFile();
        System.out.println("----------------");
        list.list(2);

    }

}
