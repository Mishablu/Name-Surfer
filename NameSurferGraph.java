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
	//initialize the instance variable that will define the size of the font in proportion to the window size
	private int fontConstant = 6;
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
			//add vertical lines
			add(new GLine(x, 0, x, getHeight()));
			//add date labels
			add(new GLabel(Integer.toString(START_DECADE+(i*10)), x , getHeight()-(GRAPH_MARGIN_SIZE/3)));
		}
		//add horizontal lines
		add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
		add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE , getWidth(), getHeight() - GRAPH_MARGIN_SIZE));
	}
	
	//this method plots the entries on the graph using connecting lines and labels at each decade
	private void plotRanking(int lineSpacing) {
		//this for loop goes through the arraylist containing the name entries and extracts these one at a time in order to graph them
		for (int i = 0 ; i < nameArray.size() ; i++) {
			NameSurferEntry entry = nameArray.get(i);				
			//this method sets the color of the line using the i of the for loop
			Color color = setColor(i);				
			//initialize x1 and y1, two variables that will save the last point in order to draw the next line in the graph
			double x1 = 0.0; 
			double y1 = 0.0;
			if (entry != null) {
				//this nested for loop goes through each ranking of the entry in order to graph it
				for (int j=0 ; j<NDECADES ; j++) {
					//get the ranking at the particular iteration from the entry
					int ranking = entry.getRank(j);
					//define the x point on the graph from the particular iteration j
					double x = j * lineSpacing;
					//define the y point on the graph using the ranking retrieved from the entry
					double y = (((getHeight() - (2*GRAPH_MARGIN_SIZE)) * (double) ranking/MAX_RANK) + GRAPH_MARGIN_SIZE);
					//define the string that will contain the name and the ranking number at that decade
					String string = entry.getName() + " " + Integer.toString(ranking);
					//if the ranking equals 0, change it to be at the bottom and not at the top of the graph and change the string to have an * instead of a ranking at the end
					if (ranking == 0) {
						y = getHeight() - GRAPH_MARGIN_SIZE;
						string = entry.getName() + "*";
					}
					//if it not the first iteration of the nested for loop (j!=0), draw a line linking last decade to the current decade
					if (j != 0) {
						drawLine(x1, y1, x , y, color);
					}
					//draw a label at the point on the vertical line representing the decade to graphically represent the ranking
					drawLabel (x, y, string, color, lineSpacing);
					//save the last x and y to use them as starting point for the line in the next iteration
					x1 = x;
					y1 = y;
				}
			}
		}
	}
	//this method sets the color of the line, alternating in order between 4 colors by using a switch statement and the i variable from the for loop in the previous method
	private Color setColor(int i) {
		switch(i % N_COLORS) {
		case 0: return Color.black;
		case 1: return Color.red;
		case 2: return Color.blue;
		case 3: return Color.magenta;
		}
		return null;
	}
	//this method draws a line of a particular color. it is used to draw the lines graphically representing the name rankings
	private void drawLine(double x1, double y1, double x, double y, Color color) {
		GLine line = new GLine(x1, y1, x, y);
		line.setColor(color);
		add(line);
	}
	//this method draws the labels displaying the name and the ranking at each decade of a specific color (depending on how many names are already on the graph) and particular size (depending on the size of the window)
	private void drawLabel(double x, double y , String string, Color color, int lineSpacing) {
		GLabel label = new GLabel(string, x , y);
		label.setColor(color);
		label.setFont("Times New Roman-" + (lineSpacing/fontConstant));
		add(label);
	}
	//this method deletes a particular entry from the graph
	public void deleteEntry(NameSurferEntry entry){
		//this if statement ensures the entry is already displayed on the graph
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
