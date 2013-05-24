/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends ConsoleProgram implements NameSurferConstants {

	private JTextField nameField;
	private int TEXTBOX_SIZE = 20;
	
	private NameSurferGraph graph;
	private NameSurferDataBase dataBase = new NameSurferDataBase("names-data.txt");
	
/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the top of the window.
 */
	public void init() {
		graph = new NameSurferGraph();
		add(graph);
		
		add(new JLabel("Name"), NORTH);
		nameField = new JTextField(TEXTBOX_SIZE);
		add(nameField, NORTH);
		nameField.addActionListener(this);
		JButton graph = new JButton("Graph");
		add(graph, NORTH);
		JButton clear = new JButton("Clear");
		add(clear, NORTH);
		addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Clear")) removeAll();
		
		else if (e.getActionCommand().equals("Graph") || e.getSource() == nameField) {
			String str = formatName(nameField.getText());
			NameSurferEntry entry = dataBase.findEntry(str);
			if (entry != null) {
				println(entry.toString());
			} else {
				println("invalid entry, please enter another name");
			}
			//format all words to Sam
			//println("Graph: " + nameField.getText());
		}
	}
	private String formatName(String str) {
		String firstLetter = str.substring(0,1).toUpperCase();
		String word = str.substring(1).toLowerCase();
		str = firstLetter + word;
		return str;
	}
}
