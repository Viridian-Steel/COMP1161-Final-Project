package ui;

import javax.swing.JFrame;

import app.ContactsApp;

public class ContactsGUI {
    private ContactsApp app;
    JFrame frame;

    public void go(ContactsApp app) {
        this.app = app;
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame ("Contacts AddressBook Management System");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new ContactsPanel());
        frame.pack();
        frame.setVisible (true);
    }
}
