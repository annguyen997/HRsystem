import java.io.*;
import javax.swing.JOptionPane;

public class Main {

   public static void main(String[] args){
    
    final int MAX_SIZE = 1000;
    Employee[] employees = new Employee [MAX_SIZE];
    Department[] departments = new Department [MAX_SIZE];

    int counter = 0;
    int prompt = 0;

  do{
         try {
         prompt = Integer.parseInt(JOptionPane.showInputDialog("What do you want to do?" + "\n" +
         "1. Add a full time employee" + "\n" + "2. Edit an employee" + "\n" + "3. List all employees" + "\n" +
         "4. Remove an employee" + "\n" + "5. Add a part time employee" + "\n" + "6. Exit Program"));
         
         if(prompt == 1) {
            counter = addFullEmployee(employees, departments, counter);
         }
         else if (prompt == 2) {
            editEmployee(employees, departments, counter);
         }
         else if (prompt == 3) {
            listEmployees(employees, departments, counter);
         }
         else if (prompt == 4) {
            counter = removeEmployee(employees, counter);
         }
         else if (prompt == 5) {
            counter = addPartTimeEmployee(employees, departments, counter);
         }
         else if (prompt == 6) {
            JOptionPane.showMessageDialog(null, "Thank you");
         }
         else {
             JOptionPane.showMessageDialog(null, "Error. Enter the number from 1-6");
         }
         
         }
         catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error. Enter a number only");
         }
      }while(prompt != 6); 

    }
    
   public static int addFullEmployee(Employee employees[], Department departments[], int counter) {
      Employee aEmployee = null;
      Department aDepartment = null;
      
      aDepartment = new Department(
           JOptionPane.showInputDialog("Enter department name")
      );
          
      boolean valid = false;
      do {
         try {
         aEmployee = new Employee (Double.parseDouble(JOptionPane.showInputDialog("Enter salary")),
         JOptionPane.showInputDialog("Enter a first name"),
         JOptionPane.showInputDialog("Enter a last name"),
         JOptionPane.showInputDialog("Enter a status"),
         aDepartment, 
         JOptionPane.showInputDialog("Enter a title")
         );
         valid = true;   
         }
      catch(NumberFormatException e){
         JOptionPane.showMessageDialog(null, "Enter a numeric value");
      }
      catch(IllegalArgumentException e) {
         JOptionPane.showMessageDialog(null, e.getMessage());
      }
      }while(valid == false);
      employees[counter] = aEmployee;
      counter++;
      return counter;
   }
   
    public static int addPartTimeEmployee(Employee employees[], Department departments[], int counter) {
      PartTimeEmployee aEmployee = null;
      Department aDepartment = null;
      
      aDepartment = new Department(
           JOptionPane.showInputDialog("Enter department name")
      );
          
      boolean valid = false;
      do {
         try {
         aEmployee = new PartTimeEmployee (Double.parseDouble(JOptionPane.showInputDialog("Enter salary")),
         JOptionPane.showInputDialog("Enter a first name"),
         JOptionPane.showInputDialog("Enter a last name"),
         JOptionPane.showInputDialog("Enter a status"),
         aDepartment, 
         JOptionPane.showInputDialog("Enter a title"),
         Integer.parseInt(JOptionPane.showInputDialog("Enter hoursWorked"))
         );
         valid = true;   
         }
      catch(NumberFormatException e){
         JOptionPane.showMessageDialog(null, "Enter a numeric value");
      }
      catch(IllegalArgumentException e) {
         JOptionPane.showMessageDialog(null, e.getMessage());
      }
      }while(valid == false);
      employees[counter] = aEmployee;
      counter++;
      return counter;
    }
     
    //not acutally removing the object. gotta figure out
    public static int removeEmployee(Employee employees[], int counter) {
       if (counter != 0) {
         int input = Integer.parseInt(JOptionPane.showInputDialog("Which employee do you want to remove? Enter an Employee ID" + "\n"));
         
         for(int i = 0; i < counter; i++) {
            if(input == employees[i].getEmployeeID()) {
               for (int j=i; j<counter-1; j++) {
                  employees[j] = employees[j+1];
                  counter--; 
               }
            }
            break;
         }
      }
      else {
         JOptionPane.showMessageDialog(null, "Error, there is no employee to remove on the list");
      }
      return counter;
    }
    
    public static void editEmployee(Employee employees[], Department departments[], int counter) {
      String option = "";
       if (counter != 0) {
         int input = Integer.parseInt(JOptionPane.showInputDialog("Which employee do you want to edit? Enter an Employee ID" + "\n"));
         
         //think need casting here too
         for(int i = 0; i < counter; i++) {
            if(input == employees[i].getEmployeeID()) {
            option = JOptionPane.showInputDialog("What do you want to edit?");
               if(option.equals("salary")) {
                  employees[i].setSalary(Double.parseDouble(JOptionPane.showInputDialog("Enter a new salary")));  
               }
               if(option.equals("title")) {
                  employees[i].setTitle(JOptionPane.showInputDialog("Enter a new title"));  
               }
           }
           else {
           JOptionPane.showMessageDialog(null, "Error, there is no employee to remove on the list");
           }
         }
      }
      }
     
    public static void listEmployees(Employee employees[], Department departments[], int counter) {
      String list = "Details of employees" + "\n" + "\n";
      if(counter == 0) {
         JOptionPane.showMessageDialog(null, "Error, there is no employee to show on the list");
      }
      else {
      for(int i=0; i < counter; i++){
            if(employees[i]instanceof Employee){
               list+= ((Employee)employees[i]).toString()+"\n";
            }
            if(employees[i]instanceof PartTimeEmployee) {
               list += ((PartTimeEmployee)employees[i]).toString() + "\n";
            }

           JOptionPane.showMessageDialog(null, list);
      }
    }
     


   }
    
 
}
 
 /*

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

*/