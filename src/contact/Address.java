/**
 * Address Class
 * @author 620156598
 * @version 
 */
package contact;
import java.util.ArrayList;

public class Address implements java.io.Serializable{

    // attribute of the address class
    private String addressLine;

    /**
	 * constructor for address accepts the adress lines
	 * @param addressLine address lines seperated by semi colons e.g. line1;line2;;;line5
	 */
	public Address(String addressLine) {
		this.addressLine = addressLine;
	}

	/**
	 * gets the country from the address
	 * @return returns the country of address
	 */
	public String getCountry() {
		String[] addrSplit = this.addressLine.split(";");
		return addrSplit[addrSplit.length - 1];
	}

	/**
	 * getAddress Address Method
	 * @return returns the address lines in a string array excluding blank lines
	 */
	public String[] getAddress() {
		
		String[] addrSplit = this.addressLine.split(";");
		ArrayList<String> strList = new ArrayList<>();

		for(String s : addrSplit) {
			if(!s.isEmpty()) {
				strList.add(s); //remove address lines that are blank
			}
		}

		return strList.toArray(new String[strList.size()]); //converts array list to array
	}

	/**
     * toString method
	 * @return the adress in a string seprated by new line
	 */ 
	public String toString() {
		String[] addArr = getAddress();
		String result="";
		for(String addr : addArr) {
			result += "\n" + addr;

		}
		return result;
	}
}