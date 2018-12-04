import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.EOFException;
import javax.swing.JFrame;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class HRSystem extends JFrame{

    public static void main(String[] args)
    {
      JFileChooser fc = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt"); 
      fc.setFileFilter(filter);
      boolean isDone = false;
      boolean fileFound = false;
      String promptMsg = "HR System\nList of service(1-5)\n1. Add New Employee\n2. Remove Employee\n3. Edit Employee Info\n4. Display All\n5. Exit\n";
      int chosenService;
      String fileName = null;
      
      //Initial setting to test
      Insurance[] insurances = {new Insurance("PlanA", 100), new Insurance("PlanB", 200), new Insurance("PlanC", 300)};
      Department[] departments = {new Department("HR", "Human_Resource"), new Department("IT", "Information_Technology"), new Department("BS", "Business")};
      
      int returnVal = fc.showOpenDialog(null);
      if(returnVal == JFileChooser.APPROVE_OPTION) 
      {
         fileFound = true;
         fileName = fc.getSelectedFile().getName();
      }
      else
      {
         JOptionPane.showMessageDialog(null, "File is not properly selected. Try it later.");
      }           

     //Scanner read = new Scanner(new FileInputStream(file));     
     if(fileFound)   
     { 
      while(!isDone)
      {
         try{
            chosenService = Integer.parseInt(JOptionPane.showInputDialog(null, promptMsg));
            if(chosenService < 1 || chosenService > 5)
            {
               throw new IllegalArgumentException("Invalid Service Range");
            }
            if(chosenService == 1)
            {
               addEmployee(fileName, insurances, departments);
               
            }
            if(chosenService == 2)
            {
               removeEmployee(fileName);
            }
            if(chosenService == 3)
            {
               editEmployee(fileName, insurances, departments);
            }            
            if(chosenService == 4)
            {
               displayAll(fileName, insurances, departments);
            }            
            if(chosenService == 5)
            {
               isDone = true;
            }
         }
         catch(NumberFormatException e)
         {
            JOptionPane.showMessageDialog(null, "Invalid Range");
         }
         catch(NullPointerException e)
         {
            JOptionPane.showMessageDialog(null, "Empty value");
         }                                                   
         catch(IllegalArgumentException e)
         {
            JOptionPane.showMessageDialog(null, e.getMessage());
         }
         catch(FileNotFoundException e)
         {
            JOptionPane.showMessageDialog(null, "File Not Found");
         }         
      } 
     }
    }

    /** Add employee method if system needs to add a new employee not originally found in text file */
    public static void addEmployee(String file, Insurance[] insurances, Department[] departments) throws FileNotFoundException
    {
      int id;
      double salary;
      String fName;
      String lName;
      boolean isFulltime = false;
      String insuranceName;
      String departmentName;      
      Insurance insurance;
      Department department;
      String msg;
      
      id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the ID: "));
      
      if(id < 0)
      {
         throw new IllegalArgumentException("Invalid ID is inserted");
      }      
      if(!verifyID(id, file))
      {
         throw new IllegalArgumentException("The inserted ID is already in the system");
      }
      
      salary = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the salary: "));
      if(salary < 0)
      {
         throw new IllegalArgumentException("Salary cannot be negative");
      }
      fName = JOptionPane.showInputDialog(null, "Enter the first name: ");
      lName = JOptionPane.showInputDialog(null, "Enter the last name: ");
      if(fName.equals("") || lName.equals(""))
      {
         throw new IllegalArgumentException("Name cannot be empty");
      }

      if(JOptionPane.showConfirmDialog(null, "Is the employee full time employee?") == JOptionPane.YES_OPTION)
      {
         isFulltime = true;
      }
      
      insuranceName = JOptionPane.showInputDialog(null, "Enter the insurance plan{PlanA,PlanB,PlanC}: ");
      insurance = verifyInsurance(insuranceName, insurances);
      if(insurance == null)
         throw new IllegalArgumentException("Inserted insurance plan does not exist");
      departmentName = JOptionPane.showInputDialog(null, "Enter the department{HR,IT,BS}: ");                              
      department = verifyDepartment(departmentName, departments);
      if(department == null)
         throw new IllegalArgumentException("Inserted department does not exist");
      if(isFulltime)
      {
         Employee newEmployee = new Employee(id, salary, fName, lName, isFulltime, insurance, department);
      }
      else
      {
         int hoursWorked = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the work-hours per week"));
         if(hoursWorked < 0)
            throw new IllegalArgumentException("work-hours cannot be negative");
         PartTimeEmployee newEmployee = new PartTimeEmployee(id, salary, fName, lName, isFulltime, insurance, department, hoursWorked);
      }
      msg = id + " " + salary + " " + fName + " " + lName + " " + isFulltime + " " + insurance.getPlanName() + " " + insurance.getAmount() + " "  + 
            department.getName() + " " + department.getDescription() + "\n";
            
      
            
      PrintWriter wr = new PrintWriter(new FileOutputStream(new File(file), true));
      wr.print(msg);
      wr.close();
            
    }
   
   
    public static boolean verifyID(int id, String file) throws FileNotFoundException
    {
      Scanner read = new Scanner(new FileInputStream(new File(file)));
      boolean eof = false;
      int idTokens;
      
      while(read.hasNext())
      {
         idTokens = Integer.parseInt(read.next());
         read.next();
         read.next();
         read.next();
         read.next();
         read.next();
         read.next();
         read.next();
         read.next();
         
         if(idTokens == id)
         {
            read.close();
            return false;
         }
      }
      
      read.close();
      
      return true;
    }
      
    public static Insurance verifyInsurance(String insuranceName, Insurance[] insurances)
    {
      if(insurances[0].getPlanName().equalsIgnoreCase(insuranceName))
      {
         return insurances[0];
      }
      if(insurances[1].getPlanName().equalsIgnoreCase(insuranceName))
      {
         return insurances[1];      
      }
      if(insurances[2].getPlanName().equalsIgnoreCase(insuranceName))
      {
         return insurances[2];      
      }  
      
      return null;          
    }

    public static Department verifyDepartment(String departmentName, Department[] departments)
    {
      if(departments[0].getName().equalsIgnoreCase(departmentName))
      {
         return departments[0];
      }
      if(departments[1].getName().equalsIgnoreCase(departmentName))
      {
         return departments[1];      
      }
      if(departments[2].getName().equalsIgnoreCase(departmentName))
      {
         return departments[2];      
      }  
      
      return null;      
    }

    public static void removeEmployee(String file) throws FileNotFoundException
    {
      int removingID;
      String record;
      Scanner lineForScan;
      String filteredContent = "";
      boolean isRemoved = false;
      int count = 0;
      
      removingID = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the ID that will be removed: "));
      if(removingID < 0)
      {
         throw new IllegalArgumentException("Invalid ID is inserted");
      }
      
      Scanner read = new Scanner(new FileInputStream(new File(file)));
      int comparedID;
      
      while(read.hasNextLine())
      {
         record = read.nextLine();          
         lineForScan = new Scanner(record);
        
         comparedID = Integer.parseInt(lineForScan.next());

        
         if(comparedID == removingID)
         {
            isRemoved = true;
         }
         else if(count == 0)
         {
            count++;
            filteredContent = record; 
         }
         else 
         {
            filteredContent = filteredContent + "\n" + record;          
         }
         
         lineForScan.close();
      }
      if(isRemoved)
      {
         JOptionPane.showMessageDialog(null, "The record is successfully removed");
      }
      else
      {
         JOptionPane.showMessageDialog(null, "The id does not exist in the system");         
      }         

      read.close();
      
      PrintWriter wr = new PrintWriter(new FileOutputStream(new File(file)));
      wr.println(filteredContent);
      wr.close();      
    }    

    public static void editEmployee(String file, Insurance[] insurances, Department[] departments) throws FileNotFoundException
    {
      int editingID;
      String record;
      Scanner lineForScan;
      boolean matchFound = false;
      String changedContent = "";
      double salary;
      String fName;
      String lName;
      boolean isFulltime = false;
      String insuranceName;
      String departmentName;      
      Insurance insurance;
      Department department;
      String msg;      
      int count = 0;
    
      editingID = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the ID that will be edited: "));
      if(editingID < 0)
      {
         throw new IllegalArgumentException("Invalid ID is inserted");
      }    
      
      Scanner read = new Scanner(new FileInputStream(new File(file)));
      while(read.hasNextLine())
      {
         record = read.nextLine();          
         lineForScan = new Scanner(record);
         
         if(lineForScan.hasNext() && Integer.parseInt(lineForScan.next()) == editingID)
         {
            matchFound = true;
         }
         else if(count == 0)
         {
            changedContent = record;
            count++;
         }
         else
         {
            changedContent = changedContent + "\n" + record;         
         }       
         lineForScan.close();   
      }
      read.close();
      
      
      if(matchFound)
      {
         salary = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the salary(edit): "));
         if(salary < 0)
         {
            throw new IllegalArgumentException("Salary cannot be negative");
         }
         fName = JOptionPane.showInputDialog(null, "Enter the first name(edit): ");
         lName = JOptionPane.showInputDialog(null, "Enter the last name(edit): ");
         if(fName.equals("") || lName.equals(""))
         {
            throw new IllegalArgumentException("Name cannot be empty");
         }
   
         if(JOptionPane.showConfirmDialog(null, "Is the employee full time employee(edit)?") == JOptionPane.YES_OPTION)
         {
            isFulltime = true;
         }
         
         insuranceName = JOptionPane.showInputDialog(null, "Enter the insurance plan{PlanA,PlanB,PlanC}(edit): ");
         insurance = verifyInsurance(insuranceName, insurances);
         if(insurance == null)
            throw new IllegalArgumentException("Inserted insurance plan does not exist");
         departmentName = JOptionPane.showInputDialog(null, "Enter the department{HR,IT,BS}(edit): ");                              
         department = verifyDepartment(departmentName, departments);
         if(department == null)
            throw new IllegalArgumentException("Inserted department does not exist");
         if(isFulltime)
         {
            Employee newEmployee = new Employee(editingID, salary, fName, lName, isFulltime, insurance, department);
         }
         else
         {
            int hoursWorked = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the work-hours per week"));
            if(hoursWorked < 0)
               throw new IllegalArgumentException("work-hours cannot be negative");
            PartTimeEmployee newEmployee = new PartTimeEmployee(editingID, salary, fName, lName, isFulltime, insurance, department, hoursWorked);
         }
         msg = editingID + " " + salary + " " + fName + " " + lName + " " + isFulltime + " " + insurance.getPlanName() + " " + insurance.getAmount() + " "  + 
               department.getName() + " " + department.getDescription();  
         changedContent = changedContent + "\n" + msg;     
         
      }
      else
      {
         throw new IllegalArgumentException("The inserted ID does not exist in the system");
      }
      
      PrintWriter wr = new PrintWriter(new FileOutputStream(new File(file)));
      wr.println(changedContent);
      wr.close();

    }    

    public static void displayAll(String file, Insurance[] insurances, Department[] departments) throws FileNotFoundException
    {
      ArrayList<Employee> employees = new ArrayList<Employee>();
      String sortMethod = "";
      int id;
      double salary;
      String fName;
      String lName;
      boolean isFulltime;
      String plan;
      int amount;
      String department;
      String departDescription;
      Employee employee;
      
      Scanner read = new Scanner(new FileInputStream(new File(file)));
      
      while(read.hasNext())
      {
         id = Integer.parseInt(read.next());
         salary = Double.parseDouble(read.next());
         fName = read.next();
         lName = read.next();
         isFulltime = Boolean.parseBoolean(read.next());
         plan = read.next();
         amount = Integer.parseInt(read.next());
         department = read.next();
         departDescription = read.next();
         
         employees.add(new Employee(id, salary, fName, lName, isFulltime, verifyInsurance(plan, insurances), verifyDepartment(department, departments)));
      }

      read.close();
      
      sortMethod = JOptionPane.showInputDialog(null, "Choose the sorting method {name, salary, insurance, department}:");
      if(sortMethod.equalsIgnoreCase("name"))
      {
         employees.sort(new CompareName());
      }
      else if(sortMethod.equalsIgnoreCase("salary"))
      {
         employees.sort(new CompareSalary());      
      }      
      else if(sortMethod.equalsIgnoreCase("insurance"))
      {
         employees.sort(new CompareInsuranceAmount());      
      }      
      else if(sortMethod.equalsIgnoreCase("department"))
      {
         employees.sort(new CompareDepartment());      
      }      
      else
      {
         throw new IllegalArgumentException("Invalid sorting method");
      }
      
      Iterator it = employees.iterator(); //iterator object is created for iteration
      while(it.hasNext())
      {
         //down casting to print the data of the users
         System.out.println(((Employee)it.next()).toString() + "\n");
      }
    }    

}
