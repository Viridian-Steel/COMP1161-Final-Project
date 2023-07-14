package contact;

import java.time.LocalDate;
import java.util.ArrayList;

public class Contact extends Person implements Comparable<Contact> {
    
    private static int counter=1;
    private int entryID;
    private int age;
    private Address address;
    private String alias;
    private ArrayList<String> email_list = new ArrayList<>();
    private ArrayList<Phone> phone = new ArrayList<>();
    private static ArrayList<String> aliases = new ArrayList<String>();
    private LocalDate currentDate = LocalDate.now();

    /**
     * Contact constructor
     * @param fname the first name of the contact person
     * @param lname the surname of the contact person
     * @param gender the gender of the contact person
     * @param dob the date of birth of the contact person
     */
    public Contact(String fname, String lname, Gender gender, long dob){
        super(fname, lname, gender, dob);
        this.entryID = getNextID();
        int year = currentDate.getYear();
        // this converts the long value stored in getDOB to a string
        String birth = String.valueOf(super.getDOB()); 
        // retrieve the day info converted to an integer
        Integer day = Integer.valueOf(birth.substring(6, 7));
        // retrieve the month info converted to an integer
        Integer month = Integer.valueOf(birth.substring(4, 6));
        // retrieve the year info converted to an integer
        Integer yob = Integer.valueOf(birth.substring(0, 4));

        if((month.intValue() < currentDate.getMonthValue()) || 
            (month.intValue() == currentDate.getMonthValue()) &&
            (day.intValue() <= currentDate.getDayOfMonth())){
                age = (year - yob.intValue());
            }
        else{
            age = (year - yob.intValue() - 1);
        }
    }

    /**
     * getNextID accessor static method
     * @return returns the incremental value that is used to set each contact's entry ID number
     */
    public static int getNextID(){
        return counter++;
    }

    /**
     * getEntryNo Accessor Method
     * @return returns the entry id number of the contact
     */
    public int getEntryNo(){
        return entryID;
    }

    /**
     * getAge method
     * @return returns the age of the contact
     */
    public int getAge(){
        return age;
    }

    /**
     * getName method
     * @return returns the name of the contact in the form lastName, firstName
     */
    public String getName(){
        String original_name = super.getName();
        String[] rearrange = original_name.split(" ");
        return new String(rearrange[1] + "," + rearrange[0]);
    }

    /**
     * updateName method
     * @param name the new surname of the contact
     */
    public void updateName(String name){
        super.changeLastName(name);;
    }

    /**
     * getAlias accessor method
     * @return returns the alias of the contact
     */
    public String getAlias(){
        return alias;
    }

    /**
     * setAlias mutator method
     * @param alias the new alias name that will replace the previous alias
     */
    public void setAlias(String alias){
        this.alias = alias;
        aliases.add(alias);
    }

    /**
     * getAddress accessor method
     * @return returns the array of adddresses
     */
    public String[] getAddress(){
        return address.getAddress();
    }

    /**
     * setAddress mutator method
     * @param address the new address
     */
    public void setAddress(String address)
    {
        this.address = new Address(address);
    }

    /**
     * addEmail method
     * @param email the email that is added to the email list
     */
    public void addEmail(String email)
    {
        this.email_list.add(email);
    }

    /**
     * deleteEmail method
     * @param email the email that will be removed/deleted from the email list
     */
    public void deleteEmail(String email){
        this.email_list.remove(email);
    }

    /**
     * getEmailList method
     * @return returns the list of emails in an array form
     */
    public String[] getEmailList(){
        String[] set_of_emails = new String[email_list.size()];

        for(int i = 0; i < email_list.size(); i++){
            set_of_emails[i] = (String)(email_list.get(i));
        }
        return set_of_emails;
    }

    /**
     * getPhoneList method
     * @return returns an array of phone numbers
     */
    public String[] getPhoneList(){
        String[] phone_list = new String[phone.size()];

        for(int i=0; i<phone.size(); i++){
            phone_list[i] = ((Phone)phone.get(i)).toString();
        }
        return phone_list;
    }

    /**
     * addPhone method
     * @param p_type the phone type (M=Mobile W=Work)
     * @param phone_num the phone number
     */
    public void addPhone(char p_type, long phone_num){
        Phone phone_contact = new Phone(phone_num, p_type);
        if (getPhoneList().length < 5){
            phone.add(phone_contact);
        }
    }

    /**
     * deletePhone mutator method
     * @param phone_num the phone number that is to be deleted
     */
    public void deletePhone(long phone_num){
        for(int i=0; i<phone.size(); i++){
            if(phone_num == ((Phone)phone.get(i)).getNumber()){
                phone.remove(i);
            }
        }
    }

    /**
     * toString Method
     * @return returns the contact information in a string format
     */
    public String toString(){
        String result = "";
        result += "ID #: " + getEntryNo() + "\n";
        result += "Name: " +  getName() + "\n";
        result += "Gender: " + super.getGender() + "\n";
        result += "Date of Birth: " + super.getDOB() + "\n";
        result += "Age: " + getAge() + "\n";
        result += "Alias: " + getAlias() + "\n";

        return result;
    }


    public String toSaveFormat(String delimiter){
        String r = "";
        r += getName() + delimiter;
        r += getGender() + delimiter;
        r += String.valueOf(getDOB()) + delimiter;

        for (String email : email_list){
            r += email + delimiter;
        }

        return r;
    }

    /**
     * // TODO: implement this method with the correct comparison replacing the 0
     * @param other The object that will be compared to
     */
    public int compareTo(Contact other){
        return 0;
    }
}
