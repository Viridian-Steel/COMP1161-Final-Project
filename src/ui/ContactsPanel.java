package ui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout.Constraints;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;

import contact.Contact;
import contact.Person;
import contact.NameComp;
import data.ContactsDB;
import app.ContactsApp;


import java.util.Comparator;
import java.util.Collections;
import java.awt.Color;


public class ContactsPanel extends JPanel{
    //create your layout manager and components, actionListeners, etc.
    // ensure it has all the requirements needed. You can create multiple panels apart from ContactsPanel
    // to make the workload simpler
    private JButton     cmdCreate;
    private JButton     cmdView;
    private JButton     cmdEdit;
    private JButton     cmdDelete;
    private JButton     cmdSort;

     private JPanel      pnlCommand;
     private JPanel      pnlDisplay;
     private JPanel      panel3;
     private JPanel      panel4;
     private JPanel      panel5;
     private JLabel      label;
     JLabel label9;
    private ContactsPanel thisForm;
    private  JScrollPane scrollPane;

    private JTable table;
    private DefaultTableModel model;
   

    private ContactsApp app;
    boolean sortbyEntryNum=false;
    

public ContactsPanel(ContactsApp app){

        this.app = app;
        setLayout(new BorderLayout());
        //thisForm=this;

        pnlDisplay = new JPanel();
        pnlDisplay.setBackground(Color.white);
        pnlDisplay.setLayout(new BoxLayout(pnlDisplay, BoxLayout.Y_AXIS));
        pnlDisplay.add(Box.createRigidArea(new Dimension(150, 200)));
        

        pnlCommand = new JPanel();
        pnlCommand.setBackground(Color.white);
        pnlCommand.setLayout(new BoxLayout(pnlCommand,BoxLayout.X_AXIS));
         
        
        String[] columnNames={"Name","Gender","Date of Birth", "Address", "Alias","Entry Number"};
        model=new DefaultTableModel(columnNames, app.getContacts().size());
        table=new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500,app.getContacts().size()*15 +50));
        table.setFillsViewportHeight(true);
        table.setBackground(Color.white);


        if (!app.getContacts().isEmpty()) {
            for(Contact c : app.getContacts()){
                ArrayList<String> d = new ArrayList<String>();
                d.add(c.getName());
                d.add(c.getGender());
                d.add(String.valueOf(c.getDOB()));
                d.add(c.getAddress()[0]); //I'm tired. Feel free to change to something better  //ok ok
                d.add(c.getAlias());
                d.add(String.valueOf(c.getEntryNo()));
                model.addRow(d.toArray());
            }
        }
        table.setModel(model); //should be fine

        scrollPane = new JScrollPane(table);

        cmdCreate = new JButton("Create Contact");
        cmdView = new JButton("View Contact");
        cmdEdit = new JButton("Edit Contact");
        cmdDelete = new JButton("Delete Contact");
        cmdSort = new JButton("Sort Contact");
      
      

        pnlCommand.add(Box.createRigidArea(new Dimension(104,0)));
        pnlCommand.add(cmdCreate);
        pnlCommand.add(Box.createHorizontalGlue());
        pnlCommand.add(cmdView);
        pnlCommand.add(Box.createHorizontalGlue());
        pnlCommand.add(cmdEdit);
        pnlCommand.add(Box.createHorizontalGlue());
        pnlCommand.add(cmdDelete);
        pnlCommand.add(Box.createHorizontalGlue());
        pnlCommand.add(cmdSort);
        pnlCommand.add(Box.createRigidArea(new Dimension(150,0)));

        


        cmdCreate.setBackground(Color.pink);
        cmdView.setBackground(Color.pink); 
        cmdEdit.setBackground(Color.CYAN);
        cmdDelete.setBackground(Color.pink);
        cmdSort.setBackground(Color.CYAN);
          

        cmdCreate.addActionListener(new CreateButtonListener());
        cmdView.addActionListener(new ViewButtonListener());
        cmdEdit.addActionListener(new EditButtonListener());
        cmdDelete.addActionListener(new DeleteButtonListener());
        cmdSort.addActionListener(new SortButtonListener());


       

        panel3 = new JPanel();
        panel3.setBackground(Color.gray);
        panel3.setPreferredSize(new Dimension(360, 240));

        panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));

        JPanel pan1 = new JPanel();
        pan1.setBackground(Color.gray);

        JPanel pan2 = new JPanel();
        pan2.setBackground(Color.white);
        pan2.setPreferredSize(new Dimension(30, 30));

        label = new JLabel("Contacts AddressBook Management System");
        pan1.add(label);

        panel4.add(pan1);
        panel4.add(pan2);

        panel5 = new JPanel();
        panel5.setBackground(Color.white);
        panel5.setPreferredSize(new Dimension(150, 150));

        // Initialize label9
        label9 = new JLabel("Client Data");
        panel5.add(label9, BorderLayout.NORTH);
        panel3.add(label9, BorderLayout.NORTH);

        panel3.add(scrollPane, BorderLayout.CENTER);

        add(panel3, BorderLayout.CENTER);
        add(panel4, BorderLayout.NORTH);
        add(panel5, BorderLayout.EAST);
        add(pnlCommand, BorderLayout.SOUTH);
        add(pnlDisplay, BorderLayout.WEST);

       
       
        
        
    }

    

   private class CreateButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        ContactsEntry contactsEntry = new ContactsEntry(app);
        
        
        for(Contact c: app.getContacts()){
                ArrayList<String> d = new ArrayList<String>();
                d.add(c.getName());
                d.add(c.getGender());
                d.add(String.valueOf(c.getDOB()));
                d.add(c.getAddress()[0]); //I'm tired. Feel free to change to something better  //ok ok
                d.add(c.getAlias());
                model.addRow(d.toArray());
            }
        
        model.fireTableDataChanged();
        table.setModel(model); //should be fine
    }
}
  private class ViewButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        
    }
}
 private class EditButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        
        String input= JOptionPane.showInputDialog(cmdCreate, "Enter Client Id");
         
        int entryNum=Integer.parseInt(input);
        if(app.searchByEntryNum(entryNum) != null){
            ContactsEntry c = new ContactsEntry(app.searchByEntryNum(entryNum), entryNum);
        }

    }
}
 private class DeleteButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        int[] selectedRows = table.getSelectedRows();
        for(int i = selectedRows.length-1; i >= 0; i--){
            int row =selectedRows[i];
            int x = Integer.parseInt( (String) model.getValueAt(row,5)); // Cannot invoke "java.lang.Integer.intValue()" because the return value of "javax.swing.table.DefaultTableModel.getValueAt(int, int)" is null
            app.removeContact(x);
            model.removeRow(row);
             //ContactsEntry contactsEntry = new ContactsEntry(app.contact,entryNum);
            JOptionPane.showMessageDialog(null,"Contact Deleted Successfully");
        }
    }
} 
private class SortButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {

        sortbyEntryNum = !sortbyEntryNum;
        
        if (sortbyEntryNum) {
            sortbyEntryNum(); // Sort by entry Number
            cmdSort.setText("Sort by Entry Number");
        } else {
            sortbyName(); // Sort by name
            cmdSort.setText("Sort by Name");
        }
    }
}

    public void sortbyName(){
      Collections.sort(app.getContacts(), new NameComp());
    }
    public void sortbyEntryNum(){
      Collections.sort(app.getContacts());
}
   
   
}
