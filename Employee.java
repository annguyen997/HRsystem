/**
 * The Employee class that contains employee information including full/part time status, insurance, department
 * position name, salary information, and employee ID.
 *
 * @author An Nguyen
 */

public class Employee {
    protected int employeeID;
    /* For part-time employees, this will instead indicate the hourly wage */
    protected double salary;
    protected String firstName;
    protected String lastName;
    /* Status indicates if the employee is a part-time or full-time employee */
    protected String status;
    protected Insurance insurance;
    protected Department department;
    protected String title;

    /* Indicates the number of employees objects */
    protected static int numberOfEmployees = 0;  //May need to be reflected in requirement changes.

    /* The employee ID that automatically generates */
    protected static int defaultEmployeeID = 1; //May need to be reflected in requirement changes.

    /* Time requirement to be classified full time */
    private static final int fullTimeRequirement = 40;  //May need to be reflected in requirement changes.

    public Employee(){
        setNumberOfEmployees();
    }

    public Employee(double salary, String firstName, String lastName,
                    String status, Insurance insurance, Department department, String title) {
        this.employeeID = assignEmployeeID();
        this.salary = salary;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.insurance = insurance;
        this.department = department;
        this.title = title;
        setNumberOfEmployees();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID='" + employeeID + '\'' +
                ", salary=" + salary +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status='" + status + '\'' +
                ", insurance=" + insurance +
                ", department=" + department +
                ", title='" + title + '\'' +
                '}';
    }

    /** Calls the toString() method of an employee instance
     * in a more callable format.
     * @return the string of the employee information.
     */
    public String reportEmployeeInfo(){
        return toString();
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

        try {
            this.salary = salary;
        } catch (Exception e){
            e.printStackTrace();
        }
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

    public void setStatus(String status) throws IllegalArgumentException {
        if (status == "Part-Time" || status == "Full=Time") {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid status set. Please enter 'Part-Time' or 'Full-Time'");
        }
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) throws IllegalArgumentException{
        if (insurance == null){
            throw new IllegalArgumentException("There is no Insurance object available.");
        }

        try {
            this.insurance = insurance;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) throws IllegalArgumentException{
        if (department == null){
            throw new IllegalArgumentException("There is no Insurance object available.");
        }

        try {
            this.department = department;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        try {
            this.title = title;
        } catch (Exception e){
            System.out.println("Invalid title name.");
            e.printStackTrace();
        }
    }

    public double calculateSalary(){
        if (getSalary() > 0.0){
            //There are 52 weeks in a year, so a full time employee will get supposedly 26 paychecks
            return getSalary() / 26;
        }
        //If there is no salary information available.
        return 0.00;
    }

    public String printPaycheck(){
        return "First Name: " + getFirstName() +
                " Last Name: " + getLastName() +
                " Total Pay: " + calculateSalary();
    }

    public static int getNumberOfEmployees(){
        return numberOfEmployees;
    }

    public static void setNumberOfEmployees(){
        numberOfEmployees += 1;
    }

    public static void reduceNumberOfEmployees(){
        numberOfEmployees -= 1;
    }

    public static void resetNumberOfEmployees(){
        numberOfEmployees = 0;
    }

    public static int getFullTimeRequirement(){
        return fullTimeRequirement;
    }
}
