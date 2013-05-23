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
/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the top of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //
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
		else if (e.getActionCommand().equals("Graph")) {
			println(nameField.getText());
		} else if (e.getSource() == nameField) {
			println(nameField.getText());
			nameField.removeAll();
		}
	}
}
