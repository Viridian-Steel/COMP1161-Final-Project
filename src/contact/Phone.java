package contact;

public class Phone {
    
	private char type;   // type of phone M=Mobile W=Work
	private long number; // 10 digit number for phone eg.8880001111

    /**
     * Constructor for phone that accepts 10 digit number and type
     * @param number represents the 10 digit number of person
     * @param type represents the type of phone M=Mobile W=Work H=Home
     */
    public Phone(long number, char type) {
		this.type = type;
		this.number = number;
	}

    /**
     * getAreaCode Method
     * @return returns the area code for the number e.g. 876
     */
    public long getAreaCode() {
		String strNum = String.valueOf(number);
		return Integer.valueOf(strNum.substring(0,3));
	}

    /**
	 * getType Accessor Method
	 * @return returns the phone type as a char M=Mobile W=Work
	 */
	public char getType() {
		return this.type;
	}

	/**
	 * getNumber accessor method
	 * @return returns the 10 digit number for phone
	 */
	public long getNumber() {
		return this.number;
	}

	/**
	 * toString method for Phone Class
	 * @return returns the string representation of phone number e.g (876) 000-0000
	 */
	public String toString(){
		String strNum = String.valueOf(number);
		return "("+getAreaCode()+")"+ " "+strNum.substring(3,6)+"-"+strNum.substring(6);
	}
}
