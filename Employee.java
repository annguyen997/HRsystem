public class Employee {
    protected int employeeID;
    /* For part-time employees, this will instead indicate the hourly wage */
    protected double salary;
    protected String firstName;
    protected String lastName;
    /* Status indicates if the employee is a part-time or full-time employee */
    protected String status;
    protected Department department;
    protected String title;

    /* Indicates the number of employees objects */
    protected static int numberOfEmployees = 0;  //May need to be reflected in requirement changes.

    /* The employee ID that automatically generates */
    protected static int defaultEmployeeID = 1; //May need to be reflected in requirement changes.

    /* Time requirement to be classified full time */
    private static final int fullTimeRequirement = 40;  //May need to be reflected in requirement changes.

    public Employee(){}

    public Employee(double salary, String firstName, String lastName,
                    String status, Department department, String title) {
        this.employeeID = assignEmployeeID();
        this.salary = salary;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.department = department;
        this.title = title;

    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID='" + employeeID + '\'' +
                ", salary=" + salary +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status='" + status + '\'' +
      
                ", department=" + department +
                ", title='" + title + '\'' +
                '}';
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) throws IllegalArgumentException {
        if (employeeID > 8){
            throw new IllegalArgumentException();
        }

        this.employeeID = employeeID;
    }

    /* These three methods may need to be added into the requirements log */

    public static int getDefaultEmployeeID(){
        return defaultEmployeeID;
    }

    public static void setDefaultEmployeeID(){
        defaultEmployeeID += 1;
    }

    public static int assignEmployeeID(){
         int idAssign = getDefaultEmployeeID();
         setDefaultEmployeeID();
         return idAssign;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) throws IllegalArgumentException{
        if (salary <= 0.00){
            throw new IllegalArgumentException("The salary value is too low.");
        }
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



  

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double calculateSalary(){
        if (getSalary() > 0.0){
            //There are 52 weeks in a year, so a full time employee will get supposedly 26 paychecks
            return getSalary() / 26;
        }
        //If there is no salary information available.
        return 0.00;
    }

    /* These three methods may need to be reflected in the requirements log */
    public static int getNumberOfEmployees(){
        return numberOfEmployees;
    }

    public static void setNumberOfEmployees(){
        numberOfEmployees += 1;
    }

    public static void resetNumberOfEmployees(){
        numberOfEmployees = 0;
    }

    /* This method may need to be reflected in requirements log */
    public static int getFullTimeRequirement(){
        return fullTimeRequirement;
    }
}