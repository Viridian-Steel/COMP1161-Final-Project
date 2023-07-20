/**
 * @author 620156598
 */
package ui;

import javax.swing.JFrame;

import app.ContactsApp;

public class ContactsGUI {
    private static ContactsApp app;
    JFrame frame;

    public void go(ContactsApp app, String[] args) {
        ContactsGUI.app = app; 
        //frame.setVisible(true);
        main(args);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame ("Contacts AddressBook Management System");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new ContactsPanel(app));
        frame.pack();
        frame.setVisible (true);
    }
}
