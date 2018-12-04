/**
 * Comparing class for name
 * @author Han Jang
 * G00783205
 * hjang25@masonlive.gmu.edu
 */
import java.util.Comparator;

public class CompareName implements Comparator<Employee>
{

	/**
	 * The method comapares two employee's name
	 * @return the result of the comparison
	 */  
   public int compare(Employee a, Employee b)
   {
      if(a.getLastName().compareToIgnoreCase(b.getLastName())==0) return 0; // equal
      else if(a.getLastName().compareToIgnoreCase(b.getLastName())<0) return -1; // a is smaller
      else return 1;//a is bigger
   }
}