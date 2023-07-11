package contact;

public class Person {
    private Name name;
    private Gender gender;
    private long DOB;

    /**
     * Person Constructor
     * @param fname the first name of the person
     * @param lname the surname of the person
     * @param gender the gender of the perosn
     * @param date the date of birth of the person
     */
    public Person(String fname, String lname, Gender gender, long date){
        name = new Name(fname, lname);
        this.gender = gender;
        DOB = date;    
    }

    /**
     * getName accessor method
     * @return returns the name of the person in the format "fname lname" e.g. Jackie Chan
     */
    public String getName(){
        return new String(name.getFirstName() + " " + name.getLastName());
    }

    /**
     * getDOB accessor method
     * @return returns the date of birth of the person
     */
    public long getDOB(){
        return this.DOB;
    }


    /**
     * getGender Accessor method
     * @return returns the gender of the person
     */
    public String getGender(){
        return this.gender.toString();
    }

    /**
     * changeLastName mutator method
     * @param newName the new surname that will replace the previous surname
     */
    public void changeLastName(String newName){
        this.name.changeLastName(newName);
    }
}
