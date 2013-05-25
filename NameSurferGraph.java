/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	//initialize the array list that will contain the different name entries as an instance variable
	private ArrayList<NameSurferEntry> nameArray;
	//initialize the maximum number of colors
	private int N_COLORS = 4;
	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		//initialize the array list that will contain the different name entries as an instance variable
		nameArray = new ArrayList<NameSurferEntry>();
	}
	
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		//check that the arraylist is not empty befor clearing it
		if (!nameArray.isEmpty()) {
			nameArray.clear();
			update();
		}
	}
	
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		//check that the arraylist doesnt already contain the entry before deleting it
		if(!nameArray.contains(entry)){
			nameArray.add(entry);
			update();
		}
	}
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		//define the space between the vertical lines
		int lineSpacing = getWidth()/ NDECADES;
		//draw the grid
		drawGrid(lineSpacing);
		//if the arraylist of name entries is not empty plot the entries on the graph
		if(!nameArray.isEmpty()) {
			plotRanking(lineSpacing);
		}
	}
	/* this method draws the grid: the vertical lines and the date labels using a for loop, and the horizontal lines on their own */
	private void drawGrid(int lineSpacing) {
		//this for loop draws the vertical lines and the labels with equal spacing
		for (int i=0 ; i < NDECADES ; i++) {
			double x = i * lineSpacing;
			add(new GLine(x, 0, x, getHeight()));
			add(new GLabel(Integer.toString(START_DECADE+(i*10)), x , getHeight()-(GRAPH_MARGIN_SIZE/3)));
		}
		add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
		add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE , getWidth(), getHeight() - GRAPH_MARGIN_SIZE));
	}
	
	//this method plots the entries on the graph using connecting lines and labels at each decade
	private void plotRanking(int lineSpacing) {
		//this for loop 
		for (int i = 0 ; i < nameArray.size() ; i++) {
			NameSurferEntry entry = nameArray.get(i);
			if (entry != null) {
				double x1 = 0.0; 
				double y1 = 0.0;
				Color color = setColor(i);
				for (int j=0 ; j<NDECADES ; j++) {
					int ranking = entry.getRank(j);
					double x = j * lineSpacing;
					double y = (((getHeight() - (2*GRAPH_MARGIN_SIZE)) * (double) ranking/MAX_RANK) + GRAPH_MARGIN_SIZE);
					String string = entry.getName() + " " + Integer.toString(ranking);
					if (ranking == 0) {
						y = getHeight() - GRAPH_MARGIN_SIZE;
						string = entry.getName() + "*";
					}
					if (j != 0) {
						drawLine(x1, y1, x , y, color);
					}
					drawLabel (x, y, string);
					x1 = x;
					y1 = y;
				}
			}
		}
	}
	private Color setColor(int i) {
		switch(i % N_COLORS) {
		case 0: return Color.black;
		case 1: return Color.red;
		case 2: return Color.blue;
		case 3: return Color.magenta;
		}
		return null;
	}
	private void drawLine(double x1, double y1, double x, double y, Color color) {
		GLine line = new GLine(x1, y1, x, y);
		line.setColor(color);
		add(line);
	}
	private void drawLabel(double x, double y , String string) {
		GLabel label = new GLabel(string, x , y);
		add(label);
	}
	public void deleteEntry(NameSurferEntry entry){
		if (nameArray.contains(entry)) {
			nameArray.remove(entry);
			update();
		}
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
