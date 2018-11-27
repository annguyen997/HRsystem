public class Department {
    private String name;
    private Employee manager;
    private String description;

    /* The number of employees in this department
    * This may need to be reflected in requirements logs*/
    private int numberOfEmployees;

    /* The number of Department objects available
     * This may need to be reflected in requirements logs*/
    private static int numberOfDepartments;

    public Department(){
    }

    public Department(String name){
        this.name = name;
        numberOfDepartments++;
    }

    public Department(String name, Employee manager){
        this.name = name;
        this.manager = manager;
        numberOfDepartments++;
    }

    public Department(String name, Employee manager, String description){
        this.name = name;
        this.manager = manager;
        this.description = description;
        numberOfDepartments++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", manager=" + manager +
                ", description='" + description + '\'' +
                '}';
    }
}
