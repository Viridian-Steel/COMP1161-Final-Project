import java.util.Scanner;

import app.ContactsApp;
import ui.ContactsGUI;
import ui.ContactsTextUI;

public class ContactProgram {
    public static void main(String[] args) {
        // for the line below set it to your own filename and delimiter for each part of the line
        ContactsApp app = new ContactsApp("filename", '|');

        ContactsTextUI text_ui = new ContactsTextUI();
        ContactsGUI gui = new ContactsGUI();
        
        String topBar = "\n***********************************************\n";
        String title = "\nSYSTEM FOR CLIENT ADDRESSBOOK MANAGEMENT SYSTEM \n\n";
        String bottomBar = "*********************************************\n";
        String heading = "MAIN MENU\n\n";
        String instruction = "Select an option below:\n\n";
        String t = "t. Text User Interface (TextUI) Menu\n";
        String g = "g. Graphical User Interface(GUI) Menu\n";
        String q = "q. Exit program\n";

        while(true){
            // Allow user to choose interface
            Scanner input = new Scanner(System.in);
            System.out.println(topBar + title + bottomBar + heading + instruction + t + g + q);
            System.out.print("Enter selection here: ");
            String selection = input.nextLine().toLowerCase();
            
            switch (selection) {
                case "T":
                case "t":
                    text_ui.go(app);
                    continue;
                case "G":
                case "g":
                    gui.main(args);
                    continue;
            }
            if (selection.equals("q")) {
                System.out.print("Are you sure you want to exit? y/n : ");
                String choice = input.nextLine().toLowerCase();
                switch (choice){
                    case "n":
                        continue;
                    case "y":
                        input.close();
                        System.exit(0);
                }
            }
        }
    }
}
