/**
 * ContactsApp Class (This class will implement the functionalities of section A.)
 * @author 620156598, 620154639, 620146910
 * @version
 */

package app;
import data.*;
import contact.*;

import java.util.*;

public class ContactsApp {
    private ContactsDB data;
    private ArrayList <Contact> contact_list = new ArrayList<Contact>();
    Scanner scan = new Scanner(System.in);
    
    /**
     * ContactsApp Constructor 
     * @param filename the File to be used for data Storage
     * 
     */
    public ContactsApp(String filename){

        data = new ContactsDB(filename);

        contact_list = data.getDb();
    }

    /**
     * Adds a contact to the list, and then saves
     * @param fname the First name
     * @param lname the Last name
     * @param gender the gender
     * @param dob    the date of birth
     * @param aliasName the Alias the contact may go by
     * @param address the Address of the contact
     */
    public void addContact(String fname, String lname, Gender gender, long dob, String address, String aliasName){
        Contact c = new Contact(fname, lname, gender, dob);
        c.setAlias(aliasName);
        c.setAddress(address);
        contact_list.add(c);
        data.updateDb(contact_list);
    }


    /**
     * Creates a new {@link Contact}, adds it to an array and then Saves.
     * @param fname the First Name
     * @param lname the Last name
     * @param gender the gender
     * @param dob    the date of birth
     * @param aliasName the alias
     * @param entryNum the entry number
     */
     public void addContact(String fname, String lname, Gender gender, long dob, String aliasName, int entryNum){
        Contact c = new Contact(fname, lname, gender, dob);
        c.setAlias(aliasName);
        contact_list.add(entryNum,c);
        data.updateDb(contact_list);
    }

    /**
     * Adds the specified Contact to the contact List and then saves
     * @param c the contact to be added
     */
    public void addContact(Contact c){
        contact_list.add(c);
        data.updateDb(contact_list);
    }

        /**
     * Adds the specified Contact to the contact List at the specified location and then saves
     * @param c the contact to be added
     * @param entryNum the location that the contact should go to Contact
     */
    public void addContact(Contact c, int entryNum){
        contact_list.add(entryNum, c);
        data.updateDb(contact_list);
    }

    public boolean dataChanged(ArrayList<Contact> prevData){
        return !contact_list.equals(prevData);
    }

    public ArrayList<Contact> getContacts(){
        return contact_list;
    }

    /**
     * Gets the Contact specified by email address. If the address does not exist, retusn "not found"
     * @param email the Email to be verified
     * @return the Contact in it's String Format
     */
    public String getEmailNow(String email){
	    // implement this method return the contact if the emal address is found, else return not found.
        for(Contact c : contact_list){
            for(String e : c.getEmailList()){
                if (e.equals(email)){
                    return c.toString(); //Is this what is wanted
                }
            }
        }
        
	    return "not found";
	}

    /**
     * Changes the Last Name of the Contact Specified by entryNum, if the Contact Exists
     * @param name the New Last name
     * @param entryNum  the entry Number of the Contact.
     */
    public void changeLastName(String name,int entryNum ){

      try{
        contact_list.get(entryNum).changeLastName(name);
      } catch( IndexOutOfBoundsException e){
        System.out.println("Index not found");
      }

	}

    /**
     * Changes the Alias of the Contact Specified by entryNum, if the Contact Exists
     * @param aliasName the new Alias
     * @param entryNum the entry Number of the Contact
     */
    public void changeAlias(String aliasName, int entryNum){
	    try{
        if(contact_list.get(entryNum).getAlias().equals(aliasName)){
            System.out.println("Alias is the same");
            return;
        }
        contact_list.get(entryNum).setAlias(aliasName);
      } catch( IndexOutOfBoundsException e){
        System.out.println("Index not found");
      }	
	}

    /**
     * Changes the Address of the Contact Specified by entryNum, if the Contact Exists
     * @param address the new Address 
     * @param entryNum the entry Number of the Contact
     */
    public void changeAddress(String address, int entryNum){
        try{
            contact_list.get(entryNum).setAddress(address);
        } catch( IndexOutOfBoundsException e){
            System.out.println("Index not found");
        }
    }

    /**
     * Changes the Phone Number of the Contact Specified by entryNum, if the Contact Exists
     * @param type the new phone number type
     * @param telephone the new telephone number of the contact
     * @param entryNum the entry Id number of the contact
     */
    public void changePhone(char type, long telephone,int entryNum ){        
	    String val = Long.toString(telephone);
        
        try {
            for(String p : contact_list.get(entryNum).getPhoneList()){
                if(p.equals(val)){
                    return;
                }
            }
            contact_list.get(entryNum).addPhone(type, telephone);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index not found");
        }
        
	}

    /**
     * Removes the Telephone Number of the Contact Specified by entryNum, if the Contact Exists
     * @param type the type of the telephone Number to be removed
     * @param telephone the telephone number to be removed
     * @param entryNum the entry number of the Contact
     */
    public void deleteTel(char type, long telephone,int entryNum ){
	    
	    String val = Long.toString(telephone);
	    try {
            for(String p : contact_list.get(entryNum).getPhoneList()){
                if(p.equals(val)){
                    contact_list.get(entryNum).deletePhone(telephone);
                    return;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index not found");
        }
	}
    
    public Contact searchByEntryNum(int eNum){
        try {
            return contact_list.get(eNum);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }


    /**
     * Changes the Email Address of the Contact Specified by entryNum, if the Contact Exists
     * @param email the new Email Address
     * @param entryNum the entry Number of the Contact
     */
    public void changeEmail(String email,int entryNum ){		
        
        try {
            for (String e : contact_list.get(entryNum).getEmailList()){ if(e.equals(email)) { return; } }
            contact_list.get(entryNum).addEmail(email);
        } catch (IndexOutOfBoundsException e) { System.out.println("Index not found"); }
	}


    /**
     * Removes the Email Address of the Contact Specified by entryNum, if the Contact Exists
     * @param email the Email Address to be removed
     * @param entryNum the entry Number of the Contact
     */
    public void deleteEmail(String email,int entryNum ){
		
        try {
            for (String e : contact_list.get(entryNum).getEmailList()){
                if(e.equals(email)){
                    contact_list.get(entryNum).deleteEmail(email);
                    return;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index not found");
        }    
    }


    /**
     * Removes the Contact at the Entry Number specified
     * @param entryNum the entry number that will be verified
     */
    public void removeContact(int entryNum){
        try{
            contact_list.remove(entryNum);
            data.updateDb(contact_list);
        }catch (IndexOutOfBoundsException e){
            System.out.println("Index not found");
        }
	}
	
    /**
     * Removes the Contact that contains the Email Address specified 
     * @param email the email address that will be verified
     */
	public void removeContact(String email){
         // implement this method by removing the contact from the contact list once email address matches
        try{
            for(Contact c : contact_list){
                for(String e : c.getEmailList()){
                    if(e.equals(email)){
                        contact_list.remove(c);
                        return;
                    }
                }
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("Index not found");
        }
	}

    /**
     * Sorts the Contacts by ID, and then returns them in String Format
     * @return returns the contacts sorted by client entry number
     */
    public String returnInfoByID(){
		Collections.sort(contact_list);
		String res = "";
		for (int i =0; i<contact_list.size(); i++){
			String emailHolder= "";
			for(String h : contact_list.get(i).getEmailList()){
				emailHolder+= h +" ";
			}
			res = " " + contact_list.get(i).getEntryNo() + contact_list.get(i).getName() + 
                emailHolder + contact_list.get(i).getGender();
		}
		return res;
	}

    public ContactsDB getDB(){
        return data;
    }

    /**
     * Sorts the Contacts by Name, and then returns them in String Format
     * @return returns the contacts sorted by client name
     */
    public String returnInfoByName(){
	    Collections.sort(contact_list, new NameComp());
	    String all = "";
	    for (int i = 0; i<contact_list.size(); i++ ){
	        String nameHolder= "";
	        for(String h : contact_list.get(i).getEmailList()){
	            nameHolder += h +" ";
	        }
	        all = " " + (contact_list.get(i).getEntryNo() + contact_list.get(i).getName() + 
                nameHolder + contact_list.get(i).getGender());
        }
	    return all;
	}

    
}
