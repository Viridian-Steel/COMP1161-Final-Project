

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class ContactsEntry extends JFrame {
    
    private JTextField  txtFirstName;       //first name
    private JTextField  txtLastName;        //last name
    private JTextField  txtGender;       //Gender
    private JTextField  txtAlias;        //Alias
    private JTextField  txtAddress;       //address
    private JTextField  txtdob;        //date of birth
    private JButton     cmdSave;
    private JButton     cmdClose;
    private JButton     cmdClearAll;

    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
    private ContactProgram ContactProgram;
    private JCheckBox    CheckBox;
  
     
  
    public ContactsEntry(ContactProgram contactProgram )
    {
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
        radiobutton=new JRadioButton("Male");
        pnlDisplay.add(CheckBox);
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
      //cmdSave.addActionListener(new SaveButtonListener());
    }
       private class CloseButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
        }
    }
   //private class SaveButtonListener implements ActionListener
    //{
        //public void actionPerformed(ActionEvent s)
        //{
            
        //}
    


}
