/**
 * @author 620154639, 620156598, 620146910
 */

package ui;


import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import app.ContactsApp;
import contact.Contact;
import contact.Gender;



public class ContactsEntry extends JFrame {
    
    private JTextField      txtFirstName;       //first name
    private JTextField      txtLastName;        //last name
    private JTextField      txtAlias;           //Alias
    private JTextField      txtAddress;         //address
    private JTextField      txtEmailAddress;  //address
    private JTextField      txtPnum;  //address
    private JTextField      txtdob;             //date of birth
    private JButton         cmdSave;            //save
    private JButton         cmdClose;           //close

    private JRadioButton    maleRButton;        // Male Gender
    private JRadioButton    femaleRButton;      // Female Gender

    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
  
    private ContactsApp app;
    
    private int entryNum;
    
	 /**
     * Constructor
     * @param app the Contact Application that Contacts Entry will use to refernce data
     * 
     * 
     */

    public ContactsEntry(ContactsApp app)
    {
  
        this.app = app;

        setTitle("Entering new Contact");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();

        pnlDisplay.add(new JLabel("First Name:")); 
        txtFirstName = new JTextField(20);
        //txtFirstName.setBounds(100,25,165,25);
        pnlDisplay.add(txtFirstName);
        
        pnlDisplay.add(new JLabel("Last Name:"));
        txtLastName = new JTextField(20);
        //txtLastName.setBounds(100,25,165,25);
        pnlDisplay.add(txtLastName);
        
    
        pnlDisplay.add(new JLabel("Address:"));
        txtAddress = new JTextField(20);
        //txtAddress.setBounds(100,25,165,25);
        pnlDisplay.add(txtAddress);

        pnlDisplay.add(new JLabel("Email Address:"));
        txtEmailAddress = new JTextField(20);
        //txtAddress.setBounds(100,25,165,25);
        pnlDisplay.add(txtEmailAddress);
        
        pnlDisplay.add(new JLabel("Phone Numbers (please add PhoneType to beginning of Phone Number):")); //this will look just fine I tell you
        txtPnum = new JTextField(20);
        //txtAddress.setBounds(100,25,165,25);
        pnlDisplay.add(txtPnum);
        
        pnlDisplay.add(new JLabel("Alias:"));
        txtAlias = new JTextField(20);
        //txtAlias.setBounds(100,25,165,25);
        pnlDisplay.add(txtAlias);
        
        pnlDisplay.add(new JLabel("Date of Birth:"));
        txtdob = new JTextField(20);
        //txtdob.setBounds(100,25,165,25);
        pnlDisplay.add(txtdob);

        pnlDisplay.add(new JLabel("Gender:"));
        maleRButton =new JRadioButton("Male");
        femaleRButton =new JRadioButton("Female");
        pnlDisplay.add(maleRButton);
        pnlDisplay.add(femaleRButton);
        
        pnlDisplay.setLayout(new GridLayout(1,4));
                

        
        cmdSave      = new JButton("Save");
        cmdClose   = new JButton("Close");

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);
         
      
        cmdClose.addActionListener(new CloseButtonListener());
        cmdSave.addActionListener(new SaveButtonListener());
        maleRButton.addActionListener(new MaleRButtonListener());
        femaleRButton.addActionListener(new FemaleRButtonListener());
    }


    /**
	 * Constructor
	 * @param app the Contact Application that Contacts Entry will use to refernce data
     * @param entryNum the location of the Contact being edited in the Contact List
	 */
    public ContactsEntry(ContactsApp app, int entryNum)
    {
        this.app = app;
        this.entryNum = entryNum;

        setTitle("Editing Contact");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        
        pnlDisplay.add(new JLabel("Last Name:"));
        txtLastName = new JTextField(app.getContacts().get(entryNum).getName().split(",")[1],20);
        pnlDisplay.add(txtLastName);

        pnlDisplay.add(new JLabel("Email Address:"));
        StringBuilder emS = new StringBuilder();
        for(String email: app.getContacts().get(entryNum).getEmailList()){ emS.append(email + ";"); }
        txtEmailAddress = new JTextField(emS.toString(), 20);
        //txtAddress.setBounds(100,25,165,25);
        pnlDisplay.add(txtEmailAddress);
        
        pnlDisplay.add(new JLabel("Phone Number (please add PhoneType to beginning of Phone Number):")); //this will look just fine I tell you
        txtPnum = new JTextField(20);
        //txtAddress.setBounds(100,25,165,25);
        pnlDisplay.add(txtPnum);


        pnlDisplay.add(new JLabel("Address:"));

        StringBuilder b = new StringBuilder();

        for(String s: app.getContacts().get(entryNum).getAddress()){
            b.append(s);
            b.append(";");
        }

        txtAddress = new JTextField(b.toString(),20);
        pnlDisplay.add(txtAddress);
        
        pnlDisplay.add(new JLabel("Alias:"));
        txtAlias = new JTextField(20);
        pnlDisplay.add(txtAlias);
        
        
        pnlDisplay.setLayout(new GridLayout(3,4));
        
        cmdSave      = new JButton("Save");
        cmdClose   = new JButton("Close");

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);
         
        
        cmdClose.addActionListener(new CloseButtonListener());
        cmdSave.addActionListener(new EditSaveButtonListener());
    }

    // male radio button
    private class MaleRButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(maleRButton.isSelected()){
                femaleRButton.setSelected(false);
            }
        }
    }
    
    //female radiobutton
    private class FemaleRButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(femaleRButton.isSelected()){
                maleRButton.setSelected(false);
            }
        }
    }
    
    //closes the window
    private class CloseButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
        }
    }
    
    //Saves the information after it has been edited
    private class EditSaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e){

            try {
                if(txtLastName.getText().isEmpty()) { app.changeLastName(txtLastName.getText(), entryNum); }
                
                if( !txtAddress.getText().isEmpty()) { app.changeAddress(txtAddress.getText(), entryNum); }
                
                if( !txtEmailAddress.getText().isEmpty() ) {app.changeEmail(txtEmailAddress.getText(), entryNum);}

                if(!txtPnum.getText().isEmpty() ) { app.changePhone(txtPnum.getText().charAt(0), Long.parseLong(txtPnum.getText().substring(1)), entryNum); }

                if(!txtAlias.getText().isEmpty()) { app.changeAlias(txtAlias.getText(), entryNum);}
                

            } catch(NumberFormatException nfE){ JOptionPane.showMessageDialog(null, "Please enter a proper date", "Error", JOptionPane.ERROR_MESSAGE); return; } 
            

            JOptionPane.showMessageDialog(null, "Edited Sucessfully!", "Edited", JOptionPane.INFORMATION_MESSAGE);

            setVisible(false);
        }
    }

    // saves the contact after information has been entered
    private class SaveButtonListener implements ActionListener
    {
       public void actionPerformed(ActionEvent s)
       {
            if (txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty() || ((!maleRButton.isSelected()) && (!femaleRButton.isSelected())) || txtAddress.getText().isEmpty() || txtEmailAddress.getText().isEmpty() || txtPnum.getText().isEmpty() || txtAlias.getText().isEmpty() || txtdob.getText().isEmpty() ) {
                JOptionPane.showMessageDialog(null, "Please fill out all fields before saving", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Gender g = Gender.MALE;
            if(maleRButton.isSelected())    { g = Gender.MALE; }
            if(femaleRButton.isSelected())  { g = Gender.FEMALE; }
            try{
                Contact c = new Contact(txtFirstName.getText(),txtLastName.getText(), g, Long.parseLong(txtdob.getText()));
                
                c.addEmail(txtEmailAddress.getText());
                c.setAddress(txtAddress.getText());
                Character ptype = txtPnum.getText().charAt(0);
                String num = txtPnum.getText().substring(1);
                c.addPhone(ptype, Long.parseLong(num));
                c.setAlias(txtAlias.getText());
                app.addContact(c);
                
            } catch(NumberFormatException nfE){ JOptionPane.showMessageDialog(null, "Please enter a proper date", "Error", JOptionPane.ERROR_MESSAGE); return; } 
            catch(IndexOutOfBoundsException iobE){JOptionPane.showMessageDialog(null, "Pnone Number was Not entered Correctly", "Error", JOptionPane.ERROR_MESSAGE); return; }


            txtFirstName.setText("");
            txtLastName.setText("");
            txtAddress.setText("");
            txtAlias.setText("");
            txtdob.setText("");
            txtEmailAddress.setText("");
            txtPnum.setText("");
            maleRButton.setSelected(false);
            femaleRButton.setSelected(false);
           
            JOptionPane.showMessageDialog(null, "Saved Sucessfully", "SAVED", JOptionPane.INFORMATION_MESSAGE);

       }
    }

}
