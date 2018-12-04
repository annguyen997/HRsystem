/**
 * Comparing class for department
 * @author Han Jang
 * G00783205
 * hjang25@masonlive.gmu.edu
 */
import java.util.Comparator;

public class CompareDepartment implements Comparator<Employee>
{

	/**
	 * The method comapares two employee's department
	 * @return the result of the comparison
	 */  
   public int compare(Employee a, Employee b)
   {
      if(a.getDepartment().getName().compareToIgnoreCase(b.getDepartment().getName())==0) return 0; // equal
      else if(a.getDepartment().getName().compareToIgnoreCase(b.getDepartment().getName())<0) return -1; // a is smaller
      else return 1;//a is bigger
   }
}