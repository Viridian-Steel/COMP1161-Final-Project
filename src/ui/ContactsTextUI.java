package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import app.ContactsApp;
import contact.Gender;

public class ContactsTextUI {
    private ContactsApp app;

    public void go(ContactsApp app){
        this.app = app;
        mainMenu();
    }

    // to be implemented
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
                    deleteContactEmailAddress();
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

    /* stub methods to be implemented */
    private void createContactMenu(){
        String heading = "\nNEW CONTACT MENU\n\n";
        while (true) {
            try{
                Scanner input = new Scanner(System.in);
                System.out.println(heading);
                System.out.print("Enter the First Name: ");
                String fname = input.nextLine();
                System.out.println("Enter the Last Name: ");
                String lname = input.nextLine();
                System.out.print("Enter the Gender: ");
                Gender gender = input.nextLine();
                System.out.print("Enter the Date of Birth: ");
                Long dob = input.nextLong();
                //app.addContact
                System.out.println();
                System.out.print("Business client registered. Press any key to continue or 'c' to go back: ");
                String choice = input.nextLine();
                if (choice.equals("c")) {
                    break;
                }
            }catch(InputMismatchException | NumberFormatException e){
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
    private void searchByEntryNo(){}
    private void updateContactLastName(){}
    private void updateContactAlias(){}
    private void updateContactAddress(){}
    private void addContactTelNo(){}
    private void deleteContactTelNo(){}
    private void addContactEmailAddress(){}
    private void deleteContactEmailAddress(){}
    private void viewContactsbyEntryNo(){}
    private void viewContactsbyEntryName(){}
    private void deleteContactByEntryNo(){}
    private void deleteContactbyEmail(){}

}