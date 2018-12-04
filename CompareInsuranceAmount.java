/**
 * Comparing class for InsuranceAmount
 * @author Han Jang
 * G00783205
 * hjang25@masonlive.gmu.edu
 */
import java.util.Comparator;

public class CompareInsuranceAmount implements Comparator<Employee>
{

	/**
	 * The method comapares two employee's InsuranceAmount
    * @return the result of the comparison
	 */  
   public int compare(Employee a, Employee b)
   {
      if(a.getInsurance().getAmount() == b.getInsurance().getAmount()) return 0; // equal
      else if(a.getInsurance().getAmount() < b.getInsurance().getAmount()) return -1; // a is smaller
      else return 1;//a is bigger
   }
}