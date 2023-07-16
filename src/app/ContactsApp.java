/**
 * ContactsApp Class (This class will implement the functionalities of section A.)
 * @author
 * @version
 */

package app;
import data.*;
import contact.*;

import java.util.*;

public class ContactsApp {
    private ContactsDB data;
    private ArrayList <Contact> contact_list = new ArrayList <Contact> ();
    Scanner scan = new Scanner(System.in);
    
    /**
     * ContactsApp Constructor (Complete this constructor)
     * @param filename
     * 
     */
    public ContactsApp(String filename){

        data = new ContactsDB(filename);

        contact_list = data.getDb();
    }

    /**
     * Adds a contact to the list, and then saves
     * @param fname
     * @param lname
     * @param gender
     * @param dob
     * @param aliasName
     */
    public void addContact(String fname, String lname, Gender gender, long dob, String aliasName){
        Contact c = new Contact(fname, lname, gender, dob);
        c.setAlias(aliasName);
        contact_list.add(c);
        data.updateDb(contact_list);
    }

    /**
     * 
     * @param email
     * @return
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
     * changeLastName Method
     * @param name
     * @param entryNum
     */
    public void changeLastName(String name,int entryNum ){

      try{
        contact_list.get(entryNum).changeLastName(name);
      } catch( IndexOutOfBoundsException e){
        System.out.println("Index not found");
      }

	}

    /**
     * changeAlias Method
     * @param aliasName
     * @param entryNum
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
     * changeAddress Method
     * @param address
     * @param entryNum
     */
    public void changeAddress(String address, int entryNum){
        try{
            contact_list.get(entryNum).setAddress(address);
        } catch( IndexOutOfBoundsException e){
            System.out.println("Index not found");
        }
    }

    /**
     * changePhone method
     * @param type the phone number type
     * @param telephone the telephone number of the contact
     * @param entryNum the entry Id number of the contact
     */
    public void changePhone(char type, long telephone,int entryNum ){        
        boolean found = false;
	    String val = Long.toString(telephone);
        
        try {
            for(String p : contact_list.get(entryNum).getPhoneList()){
                if(p.equals(val)){
                    found = true;
                    return;
                }
            }
            contact_list.get(entryNum).addPhone(type, telephone);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index not found");
        }
        
	}

    /**
     * 
     * @param type
     * @param telephone
     * @param entryNum
     */
    public void deleteTel(char type, long telephone,int entryNum ){
        /* implement this function to go through the contact list, verifying the entry number and 
         * then through the phone list that stores a set of phone numbers. If found the phone is 
         * equivalent to the val variable, the found should be set to true. 
         * If not found, delete the phone number from the contact */ //this makes no sense
	    boolean found = false;
	    String val = Long.toString(telephone);
	    try {
            for(String p : contact_list.get(entryNum).getPhoneList()){
                if(p.equals(val)){
                    found = true;
                    contact_list.get(entryNum).deletePhone(telephone);
                    return;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index not found");
        }
	}
    
    /**
     * changeEmail method
     * @param email
     * @param entryNum
     */
    public void changeEmail(String email,int entryNum ){
        /* implement this function to go through the contact list, verifying the entry number and 
        * then through the email list. If email matches the email in the email list, the found 
        * should be set to true. If not found,add the email address to the contact */
		boolean found = false;
        
        try {
            for (String e : contact_list.get(entryNum).getEmailList()){
                if(e.equals(email)){
                    found = true;
                    return;
                }
            }
            contact_list.get(entryNum).addEmail(email);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index not found");
        }
	    
	}

    public void deleteEmail(String email,int entryNum ){
        /* implement this function to go through the contact list, verifying the entry number and 
         * then through the email list. If email matches the email in the email list, the found 
         * should be set to true. If not found, delete the email address from the contact */
		boolean found = false;
        try {
            for (String e : contact_list.get(entryNum).getEmailList()){
                if(e.equals(email)){
                    found = true;
                    contact_list.get(entryNum).deleteEmail(email);
                    return;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index not found");
        }    
    }
    /**
     * removeContact method
     * @param entryNum the entry number that will be verified
     */
    public void removeContact(int entryNum){
        try{
            contact_list.remove(entryNum);
        }catch (IndexOutOfBoundsException e){
            System.out.println("Index not found");
        }
	}
	
    /**
     * removeContact method
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
     * returnInfoByID method
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
     * returnInfoByName method
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
