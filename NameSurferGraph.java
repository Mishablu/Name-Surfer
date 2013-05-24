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

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);	
	}
	
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		// You fill this in //
	}
	
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
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
		int lineSpacing = (getWidth()-(2*GRAPH_MARGIN_SIZE)) / NDECADES;
		for (int i=0 ; i <= NDECADES ; i++) {
			drawLine(GRAPH_MARGIN_SIZE+(i*lineSpacing));
			drawLabel(GRAPH_MARGIN_SIZE+(i*lineSpacing) , i);
		}
		drawHorizontalLine(GRAPH_MARGIN_SIZE);
	}
	private void drawLine(int x) {
		GLine line = new GLine(x, GRAPH_MARGIN_SIZE , x , getHeight() - GRAPH_MARGIN_SIZE);
		add(line);
	}
	private void drawHorizontalLine(int x) {
		GLine line = new GLine (x, x, getWidth() - x , x);
		GLine line2 = new GLine (x, getHeight() - x , getWidth() - x , getHeight() - x);
		add(line);
		add(line2);
	}
	private void drawLabel(int x , int i) {
		int date = 0;
		switch (i){
		case 0: date = 1900; break;
		case 1: date = 1910; break;
		case 2: date = 1920; break;
		case 3: date = 1930; break;
		case 4: date = 1940; break;
		case 5: date = 1950; break;
		case 6: date = 1960; break;
		case 7: date = 1970; break;
		case 8: date = 1980; break;
		case 9: date = 1990; break;
		case 10: date = 2000; break;
		case 11: date = 2010; break;
		}
		GLabel label = new GLabel(Integer.toString(date), x , getHeight());
		add(label);
	}
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
