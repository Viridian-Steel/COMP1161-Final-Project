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
     * @param delimiter
     */
    public ContactsApp(String filename, char delimiter){

        data = new ContactsDB(filename, delimiter);
    }

    /**
     * 
     * @param email
     * @return
     */
    public String getEmailNow(String email){
	    // implement this method return the contact if the emal address is found, else return not found.
        // do not remove the return statement below, just code before that line
	    return "not found";
	}

    /**
     * changeLastName Method
     * @param name
     * @param entryNum
     */
    public void changeLastName(String name,int entryNum ){
	 /*
      * Implement this method to edit the contact last name if entrynum is found 
      * in the contact list
      */
	}

    /**
     * changeAlias Method
     * @param aliasName
     * @param entryNum
     */
    public void changeAlias(String aliasName, int entryNum){
        // implement this method set the alias name of the contact via entry number. If the aliasName used is the same
        // as before, you should print a message regarding that, else set the alias of the contact
	    	
	}

    /**
     * changeAddress Method
     * @param address
     * @param entryNum
     */
    public void changeAddress(String address, int entryNum){
        // implement this method to verify if the entryNum exists in the contact list and then 
        // set the new address of the contact.
	 
	}

    /**
     * changePhone method
     * @param type the phone number type
     * @param telephone the telephone number of the contact
     * @param entryNum the entry Id number of the contact
     */
    public void changePhone(char type, long telephone,int entryNum ){
        /* implement this function to go through the contact list, verifying the entry number and 
         * then through the phonelist arraylist. If found the phone is equivalent to the val variable, the found 
         * should be set to true. If not found, add the phone number to the contact
         */

        boolean found = false;
	    String val = Long.toString(telephone);
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
         * If not found, delete the phone number from the contact */
	    boolean found = false;
	    String val = Long.toString(telephone);
	    
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
	    
	}

    public void deleteEmail(String email,int entryNum ){
        /* implement this function to go through the contact list, verifying the entry number and 
         * then through the email list. If email matches the email in the email list, the found 
         * should be set to true. If not found, delete the email address from the contact */
		boolean found = false;
        
	}

    /**
     * removeContact method
     * @param entryNum the entry number that will be verified
     */
    public void removeContact(int entryNum){
        // implement this method by removing the contact from the contact list once entry number matches

	}
	
    /**
     * removeContact method
     * @param email the email address that will be verified
     */
	public void removeContact(String email){
         // implement this method by removing the contact from the contact list once email address matches

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
