/**
 * @author 620154639, 620156598, 620146910
 */

package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import app.ContactsApp;
import contact.*;

public class ContactsTextUI {
    private ContactsApp app;

    public void go(ContactsApp app){
        this.app = app;
        mainMenu();
    }

    //to be implemented
    private void mainMenu(){
        String topBar = "\n***********************************************\n";
        String title = "\nSYSTEM FOR CLIENT ADDRESSBOOK MANAGEMENT SYSTEM \n\n";
        String bottomBar = "*********************************************\n";
        String heading = "MAIN MENU FOR TEXT USER INTERFACE\n\n";
        String instruction = "Select an option below:\n\n";
        String a = "a. Create A New Contact\n";
        String b = "b. Search by Entry#\n";
        String c = "c. Update Last Name\n";
        String d = "d. Update ALias\n";
        String e = "e. Update Address\n";
        String f = "f. Add a telephone number\n";
        String g = "g. Delete a telephone number\n";
        String h = "h. Add an email address\n";
        String i = "i. Delete an email address\n";
        String j = "j. Display the contact list (sorted by entry#)\n";
        String k = "k. Display the contact list (sorted by name)\n";
        String l = "l. Remove a contact by entry#\n";
        String m = "m. Remove a contact by email address\n";
        String n = "n. Exit Application\n";
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println(topBar + title + bottomBar + heading + instruction + a + b + c + d + e + f + g + h + i + j + k + l + m + n);
            System.out.print("Enter selection here: ");
            String selection = input.nextLine().toLowerCase();

            switch (selection) {
                case "a":
                    createContactMenu();
                    break;
                case "b":
                    searchByEntryNo();
                    break;
                case "c":
                    updateContactLastName();
                    break;
                case "d":
                    updateContactAlias();
                    break;
                case "e":
                    updateContactAddress();
                    break;
                case "f":
                    addContactTelNo();
                    break;
                case "g":
                    deleteContactTelNo();
                    break;
                case "h":
                    addContactEmailAddress();
                    break;
                case "i":
                    //deleteContactEmailAddress();
                    break;
                case "j":
                    viewContactsbyEntryNo();
                    break;
                case "k":
                    viewContactsbyEntryName();
                    break;
                case "l":
                    deleteContactByEntryNo();
                    break;
                case "m":
                    deleteContactbyEmail();
                    break;
            }
            if (selection.equals("n")) {
                System.out.print("Are you sure you want to exit? y/n : ");
                String choice = input.nextLine().toLowerCase();
                switch (choice){
                    case "n":
                        break;
                    case "y":
                        input.close();
                        System.exit(0);
                        // include a line to make the changes to the file
                }
            }
        }
    }

    @SuppressWarnings("resource")
    private void createContactMenu(){
        String heading = "\nNEW CONTACT MENU\n\n";
        while (true) {
            try{
                Scanner input = new Scanner(System.in);
                System.out.println(heading);
                System.out.print("Enter the First Name: ");
                String fname = input.nextLine();
                System.out.print("Enter the Last Name: ");
                String lname = input.nextLine();
                System.out.print("Enter the Gender: ");
                Gender gender = Gender.toGender(input.nextLine());
                System.out.print("Enter the Date of Birth: ");
                Long dob = input.nextLong();
                input.nextLine(); // weirdness from inputs
                System.out.print("Enter the Alias: ");
                String alias = input.nextLine();
                System.out.print("Enter the Address: ");
                String address = input.nextLine();
                app.addContact(fname, lname, gender, dob, address, alias);
                System.out.println();
                System.out.print("New Contact registered. Press any key to continue or 'n' to go back: ");
                String choice = input.nextLine();
                if (choice.equals("n")) {
                    break;
                }
            }catch(InputMismatchException | NumberFormatException e){
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void searchByEntryNo(){
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Please enter entry number to retrieve");
            int entryNum = Integer.parseInt(input.nextLine());
            Contact contact = app.searchByEntryNum(entryNum);

            if (contact == null) {
                System.out.println("Contact is not found.");
            }
            System.out.println(contact);
            } catch (NumberFormatException e) { 
                System.out.println("Input was Not a Number");
            }
            input.close();

    }
    @SuppressWarnings("resource")
    private void updateContactLastName(){
        Scanner input = new Scanner(System.in);
        try {

            viewContactsbyEntryNo();
            System.out.println("Please select the contact to change by enetring the ID number");
            int entryNum = Integer.parseInt(input.nextLine());
            System.out.println("Please enter new last name");
            String lastName = input.nextLine();
            app.changeLastName(lastName, entryNum);
        } catch (Exception e) {
             System.out.println("Something went wrong");
        }
        
    }
    @SuppressWarnings("resource")
    private void updateContactAlias(){
        Scanner input = new Scanner(System.in);
        try {
            viewContactsbyEntryNo();
             System.out.println("Please select the contact to change by entering the ID number");
             int entryNum = Integer.parseInt(input.nextLine());
             System.out.println("Please enter new alias");
             String alias = input.nextLine();
             app.changeAlias(alias, entryNum);
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }

    }
    @SuppressWarnings("resource")
    private void updateContactAddress(){
        Scanner input = new Scanner(System.in);
        try {
            viewContactsbyEntryNo();
            System.out.println("Please select the contact to change by entering the ID number");
            int entryNum = Integer.parseInt(input.nextLine());
            System.out.println("Enter address line 1");
            String addressLine1 = input.nextLine();
            System.out.println("Enter town");
            String addressTown = input.nextLine();
            System.out.println("Enter country");
            String addressCountry = input.nextLine();
            String address = addressLine1 + ";" + addressTown + ";;;" + addressCountry;
            app.changeAddress(address, entryNum);
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }
    @SuppressWarnings("resource")
    private void addContactTelNo(){
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Please select the contact to add phone number by entering the ID number");
            int entryNum = Integer.parseInt(input.nextLine());
            System.out.println("Please enter the number you want to add");
            Long number = Long.parseLong(input.nextLine());
            System.out.println("Enter type of phone (H = home, W = Work, M = Mobile)");
            char type = input.nextLine().charAt(0);
            app.changePhone(type, number, entryNum);
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }
    @SuppressWarnings("resource")
    private void deleteContactTelNo(){
         Scanner input = new Scanner(System.in);
        try {
            viewContactsbyEntryNo();
             System.out.println("Please select the contact to delete phone number by entering the ID number");
            int entryNum = Integer.parseInt(input.nextLine());
            System.out.println("Please enter the number you want to delete.");
            Long number = Long.parseLong(input.nextLine());
            System.out.println("Enetr type of phone (H = Home, W = Work, M = Mobile)");
            char type = input.nextLine().charAt(0);
            app.deleteTel(type, number, entryNum);
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }       
    }
    @SuppressWarnings("resource")
    private void addContactEmailAddress(){
         Scanner input = new Scanner(System.in);
         int entryNum = 0;
         try {
            viewContactsbyEntryNo(); // why is this here? @neeks12
            System.out.println("Please select the contact to add a email by entering the ID number");
            while(true){
                try {
                    entryNum = Integer.parseInt(input.nextLine());
                    if(app.searchByEntryNum(entryNum) == null){
                        System.out.println("Id Not found.");
                        System.out.print("would you like to re-enter the ID? Enter y for Yes, n for No: ");
                        String ans = input.nextLine();
                        if(ans.equals("y")){ continue; }
                    } else { break; }
                    
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Input was not a number"); 

    
                }

            }

                
            System.out.println("Please enter the email you want to add");
            String email = input.nextLine();

            app.searchByEntryNum(entryNum).addEmail(email);
            
            

         } catch (Exception e) {
            System.out.println("Something went wrong");
         }
    }
    //@SuppressWarnings("resource")
    //private void deleteContactEmailAddress(){
        //Scanner input = new Scanner(System.in);
        //try {       
        //} catch (Exception e) {
            //System.out.println("Something went wrong");
       //}
    //}
    private void viewContactsbyEntryNo(){
        System.out.println(app.returnInfoByID());
    }
    private void viewContactsbyEntryName(){
         System.out.println(app.returnInfoByName());
    }

    @SuppressWarnings("resource")
    private void deleteContactByEntryNo(){
         Scanner input = new Scanner(System.in);
         try {
            viewContactsbyEntryNo();
             System.out.println("Please select the contact to delete by entering the email");
             String email = input.nextLine();
             app.removeContact(email);
         } catch (Exception e) {
            System.out.println("Something went wrong");
         }
    }
    
    @SuppressWarnings("resource")
    private void deleteContactbyEmail(){
        Scanner input = new Scanner(System.in);
        try{
            viewContactsbyEntryNo();
            System.out.println("Please select the contact to delete by entering the email");
            String email = input.nextLine();
            app.removeContact(email);
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

}