import javax.swing.JOptionPane;

public class HRSystem {

    public static void main(String[] args)
    {
      boolean isDone = false;
      String promptMsg = "HR System\nList of service(1-5)\n1. Add New Employee\n2. Remove Employee\n3. Edit Employee Info\n4. Display All\n5. Exit\n";
      int chosenService; 
        
      while(!isDone)
      {
         try{
            chosenService = Integer.parseInt(JOptionPane.showInputDialog(null, promptMsg));
            if(chosenService < 1 || chosenService > 5)
            {
               throw new IllegalArgumentException("Invalid Range");
            }
            if(chosenService == 1)
            {
               addEmployee();
            }
            if(chosenService == 2)
            {
               removeEmployee();
            }
            if(chosenService == 3)
            {
               editEmployee();
            }            
            if(chosenService == 4)
            {
               displayAll();
            }            
            if(chosenService == 5)
            {
               isDone = true;
            }
         }
         catch(NumberFormatException e)
         {
            JOptionPane.showMessageDialog(null, "Invalid Range.");
         }                     
         catch(IllegalArgumentException e)
         {
            JOptionPane.showMessageDialog(null, e);
         }
      } 
    }

    /** Add employee method if system needs to add a new employee not originally found in text file */
    public static void addEmployee(){
    }

    public static void removeEmployee(){
    }    

    public static void editEmployee(){
    }    

    public static void displayAll(){
    }    


    /* Sorting methods */
    public static void alphabeticSort(){};
    public static void departmentSort(){};
    public static void insuranceAmountSort(){};
    public static void salaryAmountSort(){};

    /* Other methods */
    public static void createDepartment(){

    }
}
