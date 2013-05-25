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

	
	private ArrayList<NameSurferEntry> nameArray;
	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		nameArray = new ArrayList<NameSurferEntry>();
	}
	
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
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
		//check that this remove all doesnt cause bugzzz
		removeAll();		
		int lineSpacing = getWidth()/ NDECADES;
		drawGrid(lineSpacing);
		
	}
	public void drawGrid(int lineSpacing) {
		for (int i=0 ; i < NDECADES ; i++) {
			double x = i * lineSpacing;
			add(new GLine(x, 0, x, getHeight()));
			add(new GLabel(Integer.toString(START_DECADE+(i*10)), x , getHeight()-(GRAPH_MARGIN_SIZE/3)));
		}
		add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
		add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE , getWidth(), getHeight() - GRAPH_MARGIN_SIZE));
	}
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
