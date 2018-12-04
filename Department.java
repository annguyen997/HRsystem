/**
 * The Department class that contains department name, name of manager - an Employee object,
 * and description of the department
 *
 * @author An Nguyen
 */
public class Department {
    /* The name of the department */
    private String name;

    /* The Employee object that is designated as manager of department */
    private Employee manager;

    /* The description of the department */
    private String description;

    /* The number of employees in this department
    * This may need to be reflected in requirements logs*/
    private int numberOfEmployees;

    /* The number of Department objects available in the application (i.e. company)
     * This may need to be reflected in requirements logs*/
    private static int numberOfDepartments;

    /* Default Department constructor */
    public Department(){
    }

    /** Department constructor with department name provided
     * @param name - name of the department
     * */
    public Department(String name){
        this.name = name;
        numberOfDepartments++;
    }

    /** Department constructor with department name and manager provided
     *
     * @param name - name of the department
     * @param manager - manager of the department
     * */
    public Department(String name, Employee manager){
        this.name = name;
        this.manager = manager;
        numberOfDepartments++;
    }

    /** Department constructor with department name, department description, and manager provided
     *
     * @param name - name of the department
     * @param manager - manager of the department
     * @param description - description of the department
     * */
    public Department(String name, Employee manager, String description){
        this.name = name;
        this.manager = manager;
        this.description = description;
        numberOfDepartments++;
    }

    /** Get the name of department
     *
     *
     * @return name of the department
     */
    public String getName() {
        return name;
    }

    /** Set the name of department
     *
     * @param name - Name of the department
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Get the manager Employee object of department
     *
     * @return Employee object that is manager of the department
     */
    public Employee getManager() {
        return manager;
    }

    /** Set the manager Employee object of department
     *
     * @param manager The manager of the department
     */
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    /** Get the description of the department
     *
     * @return the description of the department
     */
    public String getDescription() {
        return description;
    }

    /** Set the description of the department
     * @return return the description of the department
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /** Print the department and its attributes
     *
     *
     * @return A string containing the department's attributes
     */
    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", manager=" + manager +
                ", description='" + description + '\'' +
                '}';
    }
}
