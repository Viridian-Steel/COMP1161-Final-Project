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
    //private ArrayList<Con> plist;
    private ContactsPanel thisForm;
    private  JScrollPane scrollPane;

    private JTable table;
    private DefaultTableModel model;
    //private int age;

    private ContactsApp app;
    

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

        for(Contact c: app.getContacts()){
            ArrayList<String> d = new ArrayList<String>();
            d.add(c.getName());
            d.add(c.getGender());
            d.add(String.valueOf(c.getDOB()));
            d.add(c.getAddress()[0]); //I'm tired. Feel free to change to something better  //ok ok
            d.add(c.getAlias());
            model.addRow(d.toArray());
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
        cmdSort.setBackground(Color.BLUE);
          

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

    
    /*private void updateDisplayData() {
        StringBuilder sb = new StringBuilder();
        for (Patient patient : patientManager.getListOfPatients()) {
            sb.append(patient.getName()).append(" ").append(patient.getTemperature()).append("                        " );
        }

        // Assuming label9 is the label that displays patient information
        label9.setText(sb.toString());
    }/* */

   private class CreateButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        ContactsEntry contactsEntry = new ContactsEntry(app);
        model.fireTableDataChanged();
    }
}
  private class ViewButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        
    }
}
 private class EditButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Entry id");
        int entryNum=input.nextInt();
        app.searchByEntryNum(entryNum);

    }
}
 private class DeleteButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        int[] selectedRows = table.getSelectedRows();
        for(int i = selectedRows.length-1; i >= 0; i--){
            int row =selectedRows[i];
            int x =(int) model.getValueAt(row,0);
            //contact_list.deleteContact(x);
            model.removeRow(row);

            //JOptionPane.showMessageDialog(ContactsEntry,"Contact Deleted Successfully");
        }
    }
} private class SortButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
    }
}

   
}
