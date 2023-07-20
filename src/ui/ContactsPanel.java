/**
 * @author 620154639, 620156598, 620146910
 */

package ui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

import contact.Contact;
import contact.NameComp;
import app.ContactsApp;


import java.util.Collections;
import java.awt.Color;


public class ContactsPanel extends JPanel{
    //create your layout manager and components, actionListeners, etc.
    // ensure it has all the requirements needed. You can create multiple panels apart from ContactsPanel
    // to make the workload simpler
    private JButton     cmdCreate; //create
    private JButton     cmdView;   // view
    private JButton     cmdEdit;    // edit
    private JButton     cmdDelete;
    private JButton     cmdSort;

     private JPanel      pnlCommand;
     private JPanel      pnlDisplay;
     private JPanel      panel3;
     private JPanel      panel4;
     private JPanel      panel5;
     private JLabel      label;
     JLabel label9;
    
    private  JScrollPane scrollPane;

    private volatile JTable table;
    private volatile DefaultTableModel model;
   

    private volatile ContactsApp app;
    boolean sortbyEntryNum=false;
    
/**
	 * Constructor
	 * @param app The Contact Application that  ContactsPanel will use for data retrieval/storage
	 */
public ContactsPanel(ContactsApp app){

        this.app = app;
        setLayout(new BorderLayout());
       

        pnlDisplay = new JPanel();
        pnlDisplay.setBackground(Color.white);
        pnlDisplay.setLayout(new BoxLayout(pnlDisplay, BoxLayout.Y_AXIS));
        pnlDisplay.add(Box.createRigidArea(new Dimension(150, 200)));
        

        pnlCommand = new JPanel();
        pnlCommand.setBackground(Color.white);
        pnlCommand.setLayout(new BoxLayout(pnlCommand,BoxLayout.X_AXIS));
         
        // column headings 
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
                d.add(c.getAddress()[0]); 
                d.add(c.getAlias());
                d.add(String.valueOf(c.getEntryNo()));
                model.addRow(d.toArray());
            }
        }
        table.setModel(model); 

        scrollPane = new JScrollPane(table);
        
        //iniatializes buttons
        cmdCreate = new JButton("Create Contact");
        cmdView = new JButton("View Contact");
        cmdEdit = new JButton("Edit Contact");
        cmdDelete = new JButton("Delete Contact");
        cmdSort = new JButton("Sort Contact");
      
      
         // adds buttons to the panel
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



        UpdateThread ut = new UpdateThread();
        Thread t = new Thread(ut);
        t.setName("Update Thread");
        t.start();

    }

    /**
	 * Handles the Updating of the Table Concurrently by means of MultiThreading
     * 
	 * 
	 */
    
    private class UpdateThread extends Thread {
        public void run(){
            ArrayList<Contact> prev = new ArrayList<Contact>();
            while(true){ //CPU intensive. Possible Future Optimization
                if(app.dataChanged(prev)){
                    for(int i = 0; i < model.getRowCount(); i++){ model.removeRow(i); }
                    if (!app.getContacts().isEmpty()) {
                        for(Contact c : app.getContacts()){
                            ArrayList<String> d = new ArrayList<String>();
                            d.add(c.getName());
                            d.add(c.getGender());
                            d.add(String.valueOf(c.getDOB()));
                            d.add(c.getAddress()[0]); 
                            d.add(c.getAlias());
                            d.add(String.valueOf(c.getEntryNo()));
                            model.addRow(d.toArray());
                        }
                    }
                    model.fireTableDataChanged();
                    table.setModel(model);
                    prev = app.getContacts();
                } 
            }
        }
    }
    
    //allows a  new Contact to be created
   private class CreateButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        @SuppressWarnings("unused")
        ContactsEntry contactsEntry = new ContactsEntry(app);
    }
    
}
  private class ViewButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String input= JOptionPane.showInputDialog(cmdCreate, "Enter Client Id");
        if(app.searchByEntryNum(Integer.parseInt(input)) != null){
            Contact c = app.searchByEntryNum(Integer.parseInt(input));
            String s = "";
            s += "ID#:           "    + c.getEntryNo() + "\n";
            s += "Name:          "    + c.getName() + "\n";
            s += "Gender:        "    + c.getGender() + "\n";
            s += "Date of Birth: "    + c.getDOB() + "\n";
            s += "Age:           "    + c.getAge() + "\n";
            StringBuilder addr = new StringBuilder();
            for(String addrLine: c.getAddress()){ addr.append(addrLine + ";"); }
            s += "Address:        "    + addr.toString() + "\n";
            s += "Current Alias: "    + c.getAlias() + "\n";
            s += "Email Addresses:\n";
            for(String email : c.getEmailList()){ s += "  * " + email + "\n"; }
            s += "Phone Numbers:\n";
            for(String phone : c.getPhoneList()){ s += "  * " + phone + "\n"; }
            JOptionPane.showMessageDialog(cmdCreate, s, "Contact #" + input, JOptionPane.PLAIN_MESSAGE);
        } else {JOptionPane.showMessageDialog(cmdCreate, "Invalid ID Entered", "Contact #" + input, JOptionPane.ERROR_MESSAGE);}
    }
}
/**
* 
*  editbutonActionlistener that allows the edit button to function
*/
 private class EditButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        
        String input= JOptionPane.showInputDialog(cmdCreate, "Enter Client Id");
         
        int entryNum=Integer.parseInt(input);
        if(app.searchByEntryNum(entryNum) != null){
            @SuppressWarnings("unused")
            ContactsEntry c = new ContactsEntry(app, entryNum);

        }

    }
}

/**
 * actionlistener for the Delete button whih deletes a Contact
*/
 private class DeleteButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        int[] selectedRows = table.getSelectedRows();
        for(int i = selectedRows.length-1; i >= 0; i--){
            int row =selectedRows[i];
            int x = Integer.parseInt( (String) model.getValueAt(row,5));
            app.removeContact(x);
            model.removeRow(row);
             //ContactsEntry contactsEntry = new ContactsEntry(app.contact,entryNum);
            JOptionPane.showMessageDialog(null,"Contact Deleted Successfully");
        }
    }
} 
/** 
 * action listner for the sort button that changes between sort by name and sort by entry number
 *  
*/ 
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
    /**
	 * sortbyname method
	 * sorts the contactlist by sort by number
	 */
    public void sortbyName(){
        Collections.sort(app.getContacts(), new NameComp());


        for(int i = 0; i < model.getRowCount(); i++){ model.removeRow(i); }
        
        for(Contact c: app.getContacts()){
                ArrayList<String> d = new ArrayList<String>();
                d.add(c.getName());
                d.add(c.getGender());
                d.add(String.valueOf(c.getDOB()));
                d.add(c.getAddress()[0]); 
                d.add(c.getAlias());
                d.add(String.valueOf(c.getEntryNo()));
                model.addRow(d.toArray());
            }
        
        model.fireTableDataChanged();
        table.setModel(model); 

    }

    /**
	 * sortbyEntryNum method
	 *  sorts the contactlist by sort by number
	 */

    public void sortbyEntryNum(){
        Collections.sort(app.getContacts());

        for(int i = 0; i < model.getRowCount(); i++){ model.removeRow(i); }
        
        for(Contact c: app.getContacts()){
            if(c == null) {
                app.getContacts().remove(c);
                continue;
            }
            ArrayList<String> d = new ArrayList<String>();
            d.add(c.getName());
            d.add(c.getGender());
            d.add(String.valueOf(c.getDOB()));
            d.add(c.getAddress()[0]); 
            d.add(c.getAlias());
            d.add(String.valueOf(c.getEntryNo()));
            model.addRow(d.toArray());
        }
        
        model.fireTableDataChanged();
        table.setModel(model); 
}
   
   
}
