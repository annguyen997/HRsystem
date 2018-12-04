import javax.swing.JOptionPane;

public class HRSystem {

    public static void addEmployee(){
        String firstName = JOptionPane.showInputDialog("Enter first name of employee: ");
        String lastName = JOptionPane.showInputDialog("Enter last name of employee: ");
        String status = JOptionPane.showInputDialog("Is " + firstName + " " + lastName + " a part-time or full-time employee?: ");
        double salary = Integer.parseInt(JOptionPane.showInputDialog("Enter the salary?: "));
        String title = JOptionPane.showInputDialog("Enter the title of position: ");

        /* String insurance = JOptionPane.showInputDialog("What is the insurance of + " firstName + " "
                        + lastName + "?"); */
        /* String department = JOptionPane.showInputDialog("Enter department of + firstName + " " + lastName + ": "); */

        /* For part-time employees, this will instead indicate the hourly wage */
        if (status == "Part-Time"){
            
        }
        protected double salary;
        protected String firstName;
        protected String lastName;
        /* Status indicates if the employee is a part-time or full-time employee */
        protected String status;
        protected Insurance insurance;
        protected Department department;
        protected String title;

    };
    public static Employee removeEmployee(String employeeID){ return null;};
    public static void editEmployee(String employeeID){};
    public static String displayEmployees(){ return null;};

    /* Sorting methods */
    public static void alphabeticSort(){};
    public static void departmentSort(){};
    public static void insuranceAmountSort(){};
    public static void salaryAmountSort(){};

    /* Other methods */
    public static void createDepartment(){

    }
}