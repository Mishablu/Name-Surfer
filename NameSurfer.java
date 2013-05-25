/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	private JTextField nameField;
	private int TEXTBOX_SIZE = 20;
	
	private NameSurferGraph graph;
	private NameSurferDataBase dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
	
/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the top of the window.
 */
	public void init() {
		
		//add the name text indicating that a name should be inserted into the text box
		add(new JLabel("Name"), NORTH);
		//add the text field where the user inserts the name he wants to search
		nameField = new JTextField(TEXTBOX_SIZE);
		add(nameField, NORTH);
		nameField.addActionListener(this);
		//add the button to graph the name inserted
		add(new JButton ("Graph"), NORTH);
		//add the button that clears the graph
		add(new JButton("Clear"), NORTH);
		//add the button that deletes the graph of the name in the text field
		add(new JButton("Delete"), NORTH);
		
		addActionListeners();
		
		graph = new NameSurferGraph();
		add(graph);
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		
		//if the user clicks on the clear button, clear the graph
		if(e.getActionCommand().equals("Clear")){
			graph.clear();
		//if the user clicks on the graph button or presses enter	
		} else if (e.getActionCommand().equals("Graph") || e.getSource() == nameField) {
			//get the name inserted in the text field and retrieve the namesurferentry related to the name inserted
			String str = formatName(nameField.getText());
			NameSurferEntry entry = dataBase.findEntry(str);
			//if the entry exists in the database, add the entry to the graph (display it)
			if (entry != null) {
				graph.addEntry(entry);
			}
		
		} else if (e.getActionCommand().equals("Delete")){
			String str = formatName(nameField.getText());
			NameSurferEntry entry = dataBase.findEntry(str);
			if (entry != null) {
				graph.deleteEntry(entry);
			}
		}
	}
	private String formatName(String str) {
		String firstLetter = str.substring(0,1).toUpperCase();
		String word = str.substring(1).toLowerCase();
		str = firstLetter + word;
		return str;
	}
}
