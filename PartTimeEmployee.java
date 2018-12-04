public class PartTimeEmployee extends Employee {

    /* Indicates the expected number of hours this part-time employee works, optimally per week*/
    protected int hoursWorked;

    public PartTimeEmployee(){};

    public PartTimeEmployee(double salary, String firstName, String lastName,
                            String status, Insurance insurance, Department department, String title,
                            int hoursWorked){
        super(salary, firstName, lastName, status, insurance, department, title);
        this.hoursWorked = hoursWorked;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        if (hoursWorked >= Employee.getFullTimeRequirement()) {
            setStatus("Full-Time");
        }
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        if (getStatus() == "Full-Time"){
            super.calculateSalary(); 
        }
        
        if (getSalary() <= 0.00){
            //If there is no salary information available.
            return 0.00;
        }
        return getSalary() * getHoursWorked();
    }

    @Override
    public String toString() {
        return "PartTimeEmployee{" +
                "employeeID='" + employeeID + '\'' +
                ", salary=" + salary +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status='" + status + '\'' +
                ", insurance=" + insurance +
                ", department=" + department +
                ", title='" + title + '\'' +
                "hoursWorked=" + hoursWorked +
                '}';
    }
}
