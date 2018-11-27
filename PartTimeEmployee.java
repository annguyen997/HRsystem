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

    //Fix this method implementation regarding if number of hours worked is at least 40 hours.
    //What if a part-time employee becomes full time?
    public void setHoursWorked(int hoursWorked) {
        if (hoursWorked >= Employee.getFullTimeRequirement()) {
            setStatus("Full-Time");
            //Find a way to possibly reclassify this object into an Employee object as this person is no longer
            //a part-time employee.
        }
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
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
