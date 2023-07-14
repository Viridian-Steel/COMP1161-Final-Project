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
import java.awt.BorderLayout;
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
        
}