/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	//initialize the array of integers where the rankings will be stored as an instance variable
	private int[] rankingArray;
	//initialize the string that will contain the name of each line of the data file
	private String name;
	
/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		//initialize the array that will contain the rankings to the proper amount of decades
		rankingArray = new int[NDECADES];
		//use the tokenizer to tokenize the name and then the rankings (and save these to the array)
		StringTokenizer tokenizer = new StringTokenizer(line);
		name = tokenizer.nextToken();
		for (int i=0 ; i < rankingArray.length ; i++) {
			rankingArray[i] = Integer.parseInt(tokenizer.nextToken());
		}
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		return rankingArray[decade];
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		//initialize the string
		String string = "";
		//the for loop changes the integers (rankings) to strings in ordert to return them as such in the proper format
		for (int i = 0; i < rankingArray.length; i++) {
			if (i != 0) {
				string += " ";
			}
			string += Integer.toString(rankingArray[i]);
		}
		//return the name and the rankings in the proper format
		return name + " [" + string + "]";
	}
}

