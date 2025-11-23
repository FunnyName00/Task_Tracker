import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;



public class FileGestion {
    public static void createFile() {
        try {
            File myObj = new File("task.json"); // Create File object
            if (myObj.createNewFile()) {// Try to create the file
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace(); // Print error details
        }
    }



    public static void appendToFile(String text){
        try {
            FileWriter myWriter = new FileWriter("task.json", true);
            myWriter.write("\n"+text);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    public static void readFile(){
        File myObj = new File("task.json");

        // try-with-resources: Scanner will be closed automatically
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
