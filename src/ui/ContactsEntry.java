package ui;


import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import app.ContactsApp;
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
        
        pnlDisplay.add(new JLabel("Gender:"));
        maleRButton =new JRadioButton("Male");
        femaleRButton =new JRadioButton("Female");
        pnlDisplay.add(maleRButton);
        pnlDisplay.add(femaleRButton);

        maleRButton.addActionListener(new MaleRButtonListener());
        femaleRButton.addActionListener(new FemaleRButtonListener());
        
        
        pnlDisplay.add(new JLabel("Address:"));
        txtAddress = new JTextField(20);
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
        cmdSave.addActionListener(new SaveButtonListener());
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
    private class SaveButtonListener implements ActionListener
    {
       public void actionPerformed(ActionEvent s)
       {
           if (txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty() || ((!maleRButton.isSelected()) && (!femaleRButton.isSelected())) || txtAddress.getText().isEmpty() || txtAlias.getText().isEmpty() || txtdob.getText().isEmpty() ) {
                System.out.println("You need to fill out  all fields"); 
           }
           Gender g = Gender.MALE;
           if(maleRButton.isSelected()) {g = Gender.MALE;}
           if(femaleRButton.isSelected()) {g = Gender.FEMALE;}
           try{
               app.addContact(txtFirstName.getText(),txtLastName.getText(), g, Long.parseLong(txtdob.getText()), txtAlias.getText());
           }catch(NumberFormatException nfE){
             System.out.println("You need to enter a proper Date"); 
           }
       }
    }


}
