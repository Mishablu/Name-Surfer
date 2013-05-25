import java.io.*;
import acm.util.ErrorException;
import acmx.export.java.util.*;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
	//initialize the hashmap where the names and rankings from the database will be stored.
	private Map nameMap = new HashMap();
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		
		//open and read file
		try{
			BufferedReader rd = new BufferedReader(new FileReader(filename));
			
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				//save and add each new line of the file into the hashmap
				NameSurferEntry entry = new NameSurferEntry(line);
				nameMap.put(entry.getName(), entry);
			}
		//catch and throw exceptions
		} catch(Exception e) {
			throw new ErrorException(e);
		}
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		if (nameMap.containsKey(name)) {
			return (NameSurferEntry) nameMap.get(name);
		}
		return null;
	}
}

