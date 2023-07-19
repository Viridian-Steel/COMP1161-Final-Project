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
    private JTextField      txtdob;             //date of birth
    private JButton         cmdSave;
    private JButton         cmdClose;

    private JRadioButton    maleRButton;        // Male Gender
    private JRadioButton    femaleRButton;      // Female Gender

    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
  
    private ContactsApp app;
    
    private int entryNum;

    public ContactsEntry(ContactsApp app )
    {
        this.app = app;
        setTitle("Entering new Contact");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();

        pnlDisplay.add(new JLabel("First Name:")); 
        txtFirstName = new JTextField(20);
        pnlDisplay.add(txtFirstName);
        
        pnlDisplay.add(new JLabel("Last Name:"));
        txtLastName = new JTextField(20);
        pnlDisplay.add(txtLastName);
        
    
        pnlDisplay.add(new JLabel("Address:"));
        txtAddress = new JTextField(20);
        pnlDisplay.add(txtAddress);
        
        pnlDisplay.add(new JLabel("Alias:"));
        txtAlias = new JTextField(20);
        pnlDisplay.add(txtAlias);
        
        pnlDisplay.add(new JLabel("Date of Birth:"));
        txtdob = new JTextField(20);
        pnlDisplay.add(txtdob);

        pnlDisplay.add(new JLabel("Gender:"));
        maleRButton =new JRadioButton("Male");
        femaleRButton =new JRadioButton("Female");
        pnlDisplay.add(maleRButton);
        pnlDisplay.add(femaleRButton);
        
        pnlDisplay.setLayout(new GridLayout(3,4));
                

        
        cmdSave      = new JButton("Save");
        cmdClose   = new JButton("Close");

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);
         
        //this.ContactProgram=contactProgram;
        cmdClose.addActionListener(new CloseButtonListener());
        cmdSave.addActionListener(new SaveButtonListener());
        maleRButton.addActionListener(new MaleRButtonListener());
        femaleRButton.addActionListener(new FemaleRButtonListener());
    }

    public ContactsEntry(Contact contact, int entryNum)
    {
        this.entryNum = entryNum;
        setTitle("Editing Contact");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();

        pnlDisplay.add(new JLabel("First Name:")); 
        txtFirstName = new JTextField(contact.getName().split(", ")[0],20);
        pnlDisplay.add(txtFirstName);
        
        pnlDisplay.add(new JLabel("Last Name:"));
        txtLastName = new JTextField(contact.getName().split(", ")[1],20);
        pnlDisplay.add(txtLastName);
        
        pnlDisplay.add(new JLabel("Gender:"));
        maleRButton =new JRadioButton("Male");
        femaleRButton =new JRadioButton("Female");

        if(contact.getGender().equals("MALE")){ maleRButton.setSelected(true); }
        else{femaleRButton.setSelected(true);}
        pnlDisplay.add(maleRButton);
        pnlDisplay.add(femaleRButton);

        maleRButton.addActionListener(new MaleRButtonListener());
        femaleRButton.addActionListener(new FemaleRButtonListener());
        
        
        pnlDisplay.add(new JLabel("Address:"));

        StringBuilder b = new StringBuilder();

        for(String s: contact.getAddress()){
            b.append(s);
            b.append(", ");
        }

        txtAddress = new JTextField(b.toString(),20);
        pnlDisplay.add(txtAddress);
        
        pnlDisplay.add(new JLabel("Alias:"));
        txtAlias = new JTextField(20);
        pnlDisplay.add(txtAlias);
        
        pnlDisplay.add(new JLabel("Date of Birth:"));
        txtdob = new JTextField(20);
        pnlDisplay.add(txtdob);
        
        pnlDisplay.setLayout(new GridLayout(3,4));
                

        
        cmdSave      = new JButton("Save");
        cmdClose   = new JButton("Close");

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);
         
        //this.ContactProgram=contactProgram;
        cmdClose.addActionListener(new CloseButtonListener());
        cmdSave.addActionListener(new EditSaveButtonListener());
    }


    private class MaleRButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(maleRButton.isSelected()){
                femaleRButton.setSelected(false);
            }
        }
    }
    private class FemaleRButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(femaleRButton.isSelected()){
                maleRButton.setSelected(false);
            }
        }
    }

    private class CloseButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
        }
    }

    private class EditSaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
             if (txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty() || ((!maleRButton.isSelected()) && (!femaleRButton.isSelected())) || txtAddress.getText().isEmpty() || txtAlias.getText().isEmpty() || txtdob.getText().isEmpty() ) {
                JOptionPane.showMessageDialog(null, "Please fille out all fields before saving", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Gender g = Gender.MALE;
            if(maleRButton.isSelected()) {g = Gender.MALE;}
            if(femaleRButton.isSelected()) {g = Gender.FEMALE;}
            try {
               app.removeContact(entryNum);
               app.addContact(txtFirstName.getText(),txtLastName.getText(), g, Long.parseLong(txtdob.getText()), txtAlias.getText(), entryNum);
            } catch(NumberFormatException nfE){
                JOptionPane.showMessageDialog(null, "Please enter a proper date", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            setVisible(false);
        }
    }


    private class SaveButtonListener implements ActionListener
    {
       public void actionPerformed(ActionEvent s)
       {
           if (txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty() || ((!maleRButton.isSelected()) && (!femaleRButton.isSelected())) || txtAddress.getText().isEmpty() || txtAlias.getText().isEmpty() || txtdob.getText().isEmpty() ) {
                JOptionPane.showMessageDialog(null, "Please fille out all fields before saving", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
           }
           Gender g = Gender.MALE;
           if(maleRButton.isSelected()) {g = Gender.MALE;}
           if(femaleRButton.isSelected()) {g = Gender.FEMALE;}
           try{
               app.addContact(txtFirstName.getText(),txtLastName.getText(), g, Long.parseLong(txtdob.getText()), txtAddress.getText(), txtAlias.getText());
           }catch(NumberFormatException nfE){
             JOptionPane.showMessageDialog(null, "Please enter a proper date", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
           }


           txtFirstName.setText("");
           txtLastName.setText("");
           txtAddress.setText("");
           txtAlias.setText("");
           txtdob.setText("");
           maleRButton.setSelected(false);
           femaleRButton.setSelected(false);

           JOptionPane.showMessageDialog(null, "Saved Sucessfully", "SAVED", JOptionPane.INFORMATION_MESSAGE);

       }
    }


}
