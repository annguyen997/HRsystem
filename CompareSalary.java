/**
 * Comparing class for salary
 * @author Han Jang
 * G00783205
 * hjang25@masonlive.gmu.edu
 */
import java.util.Comparator;

public class CompareSalary implements Comparator<Employee>
{

	/**
	 * The method comapares two employee's salary
	 * @return the result of the comparison
	 */  
   public int compare(Employee a, Employee b)
   {
      if(a.getSalary() == b.getSalary()) return 0; // equal
      else if(a.getSalary() < b.getSalary()) return -1; // a is smaller
      else return 1;//a is bigger
   }
}