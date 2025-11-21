

public class Main {

    public static void main(String[] args){

        taskList list = new taskList();

        list.add("Sortir le chien");
        list.add("Manger des céréales");
        list.add("Faire maths discrètes");
        System.out.println("----------------");
        list.list();
        System.out.println("----------------");
        list.update(0, "Sortir le chat");
        System.out.println("----------------");
        list.list();

    }

}
