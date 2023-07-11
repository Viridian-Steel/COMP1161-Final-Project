/**
 * Name Class
 * @author
 * @version
 */
package contact;

public class Name {
    
    // attributes of the Name class
    private String first_name, last_name;

    /**
     * Name Constructor 
     * @param firstname represents the first name
     * @param lastname represents the surname
     */
    public Name(String firstname, String lastname){
        this.first_name = firstname;
        this.last_name = lastname;
    }

    /**
     * getFirstName Method
     * @return returns the first name of the person
     */
    public String getFirstName(){
        return this.first_name;
    }

    /**
     * getLastName Method
     * @return returns the surname of the person
     */
    public String getLastName(){
        return this.last_name;
    }

    /**
     * changeName mutator method
     * @param newName the name that will replace the previous surname
     */
    public void changeLastName(String newName){
        this.last_name = newName;
    }
}
