import java.io.*;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args){

        //Read the file to upload employee data for HR
        try {
            readFile();
        } catch (FileNotFoundException e){
            System.out.println("File is not found. Cannot populate existing employee data");
            //Find a way to parse in the path of the file.
        }


    };

    public static void readFile() throws FileNotFoundException{

    }

    public static void writeFile(){};
}
