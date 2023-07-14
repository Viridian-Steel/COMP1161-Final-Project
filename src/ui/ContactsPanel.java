package ui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;

import contact.Person;

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
    //Pri
    //private ArrayList<Con> plist;
    private ContactsPanel thisForm;
    private  JScrollPane scrollPane;

    private JTable table;
    private DefaultTableModel model;
    //private int age;

public ContactsPanel(){
    setLayout(new BorderLayout());

        pnlDisplay = new JPanel();
        pnlDisplay.setBackground(Color.white);
        pnlDisplay.setLayout(new BoxLayout(pnlDisplay, BoxLayout.Y_AXIS));
        pnlDisplay.add(Box.createRigidArea(new Dimension(50, 200)));

    
        pnlCommand.add(buttonSort);
        pnlDisplay.add(Box.createRigidArea(new Dimension(150, 0)));

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

        label = new JLabel("Appointments Management System (Ams) v1.0");
        pan1.add(label);

        panel4.add(pan1);
        panel4.add(pan2);

        panel5 = new JPanel();
        panel5.setBackground(Color.white);
        panel5.setPreferredSize(new Dimension(150, 150));

        // Initialize label9
        label9 = new JLabel("Data will be displayed here");
        panel5.add(label9);
        panel3.add(label9);

        add(panel3, BorderLayout.CENTER);
        add(panel4, BorderLayout.NORTH);
        add(panel5, BorderLayout.EAST);
        add(panel1, BorderLayout.WEST);
        add(panel2, BorderLayout.SOUTH);

        // Add the listener to the "Sort" button
        buttonSort.addActionListener(new SortButtonListener());

        
        patientManager = new PatientManager();

        
        updateDisplayData();
    }

    
    private void updateDisplayData() {
        StringBuilder sb = new StringBuilder();
        for (Patient patient : patientManager.getListOfPatients()) {
            sb.append(patient.getName()).append(" ").append(patient.getTemperature()).append("                        " );
        }

        // Assuming label9 is the label that displays patient information
        label9.setText(sb.toString());
    }

   private class SortButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
       
        sortByTemperature = !sortByTemperature;

        if (sortByTemperature) {
            patientManager.sortbyTemperature(); // Sort by temperature
            buttonSort.setText("Sort by Name");
        } else {
            patientManager.sortbyName(); // Sort by name
            buttonSort.setText("Sort by Temperature");
        }

        updateDisplayData();
    }
}

   
}

        
}

}