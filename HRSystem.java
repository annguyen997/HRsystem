/**
 * HR CLASS: IMPLEMENTATION
 * @author Han Jang
 * G00783205
 * hjang25@masonlive.gmu.edu
 */
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
      JFileChooser fc = new JFileChooser(); //GUI
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt"); 
      fc.setFileFilter(filter); //FILTER FOR TXT FILE
      boolean isDone = false; //USER IS DONE
      boolean fileFound = false; 
      String promptMsg = "HR System\nList of service(1-5)\n1. Add New Employee\n2. Remove Employee\n3. Edit Employee Info\n4. Display All\n5. Exit\n";
      int chosenService; //CHOSEN SERVICE
      String fileName = null;
      
      //Initial setting for test
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

          
     if(fileFound)   
     { 
      while(!isDone)//loops until user is done
      {
         try{
            chosenService = Integer.parseInt(JOptionPane.showInputDialog(null, promptMsg));
            if(chosenService < 1 || chosenService > 5) //service range
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

	/**
	 * The method adds employee to the system
	 * @param file file name
	 * @param insurances pre-defined insurance list	
	 * @param departments pre-defined department list
    */  
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
      insurance = verifyInsurance(insuranceName, insurances); //return the matching insurance object with the insurance name
      if(insurance == null)
         throw new IllegalArgumentException("Inserted insurance plan does not exist");
      departmentName = JOptionPane.showInputDialog(null, "Enter the department{HR,IT,BS}: ");                              
      department = verifyDepartment(departmentName, departments); //return the matching department object with the department name
      if(department == null)
         throw new IllegalArgumentException("Inserted department does not exist");
      if(isFulltime)//depends on the user's case, different object is created
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
      // record to be save in txt file
      msg = id + " " + salary + " " + fName + " " + lName + " " + isFulltime + " " + insurance.getPlanName() + " " + insurance.getAmount() + " "  + 
            department.getName() + " " + department.getDescription() + "\n";
            
      
      // new entry is saved in the txt file.      
      PrintWriter wr = new PrintWriter(new FileOutputStream(new File(file), true));
      wr.print(msg);
      wr.close();
            
    }
   
	/**
	 * The method checks if the id is duplicated employee id
	 * @param id employee id being tested
	 * @param file storage file
    */     
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
            return false; //dupe is found
         }
      }
      
      read.close();
      
      return true; // no dupe exist
    }
    
	/**
	 * The method returns matching insurance to the given name
	 * @param insuranceName passed name for matching
	 * @param insurances list of insurances
    */       
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

	/**
	 * The method returns matching department to the given name
	 * @param departmentName passed name for matching
	 * @param departments list of departments
    */       
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

	/**
	 * The method removes employee
	 * @param file system storage
    */       
    public static void removeEmployee(String file) throws FileNotFoundException
    {
      int removingID; // id that needs to be removed
      String record;
      Scanner lineForScan;
      String filteredContent = ""; // content after removal
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
        
         if(comparedID == removingID) // check if the id is found
         {
            isRemoved = true;
         }
         else if(count == 0) // for the first record, different operation is required for txt file organization
         {
            count++;
            filteredContent = record; //unmatching record will be saved         
         }
         else 
         {
            filteredContent = filteredContent + "\n" + record;//unmatching record will be saved                   
         }
         
         lineForScan.close();
      }
      if(isRemoved)// confirm message
      {
         JOptionPane.showMessageDialog(null, "The record is successfully removed");
      }
      else
      {
         JOptionPane.showMessageDialog(null, "The id does not exist in the system");         
      }         

      read.close();
      // write the result to the txt file
      PrintWriter wr = new PrintWriter(new FileOutputStream(new File(file)));
      wr.println(filteredContent);
      wr.close();      
    }    

	/**
	 * The method edits the txt file
	 * @param file file name
	 * @param insurances pre-defined insurance list	
	 * @param departments list of departments
    */       
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
         
         if(lineForScan.hasNext() && Integer.parseInt(lineForScan.next()) == editingID)//match found
         {
            matchFound = true;
         }
         else if(count == 0) // first line treated different due to better organization in txt file
         {
            changedContent = record; //unmatching record will be saved         
            count++;
         }
         else
         {
            changedContent = changedContent + "\n" + record; //unmatching record will be saved         
         }       
         lineForScan.close();   
      }
      read.close();
      
      
      if(matchFound) // once match is found edit is going to be overwritten
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
      
      //overwrite the txt file
      PrintWriter wr = new PrintWriter(new FileOutputStream(new File(file)));
      wr.println(changedContent);
      wr.close();

    }    

	/**
	 * The method that displays the sorted data
	 * @param file file name
	 * @param insurances pre-defined insurance list	
	 * @param departments list of departments
    */    
    public static void displayAll(String file, Insurance[] insurances, Department[] departments) throws FileNotFoundException
    {
      ArrayList<Employee> employees = new ArrayList<Employee>(); // array list is added for sort
      String sortMethod = ""; // specified sort method
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
      
      while(read.hasNext()) // complete the ArrayList
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
     
      //depends on the chosen method, different comparable class is used.
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
      
      //iterator for output.
      Iterator it = employees.iterator(); //iterator object is created for iteration
      String msg = "REPORT: \n";
      boolean isEmpty = true;
      
      while(it.hasNext())
      {
         isEmpty = false;
         //down casting to print the data of the users
         msg += (((Employee)it.next()).toString() + "\n");
      }
      
      if(isEmpty)
         JOptionPane.showMessageDialog(null, "File is Empty");
      else
         JOptionPane.showMessageDialog(null, msg);
    }    

}
