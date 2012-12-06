package edu.cmu.phoenix.changecounter;

/****************************************
 * 
 * ModifiedLine includes an added or 
 * removed line between versions.
 * @author Daniel Souza <souzad@cmu.edu>
 *
 ****************************************/

public class ModifiedLine {
	
	//Possible kinds of modified lines
	public static enum Type {
	    Added, Deleted 
	}
	
	//The modified line text
	private String lineText;
	//The location of the modified line
	private int lineNumber;
	//The kind of modified line (added or removed)
	private Type type;
	
	/**
	 * Default constructor
	 */
	public ModifiedLine() {
		
	}
	//
	/**
	 * Constructor
	 * @param line the textual line of code that has been modified (added or removed)
	 * @param added true if a new line, false if a removed line
	 * @param number the line number of the modification
	 */
	public ModifiedLine(String line, boolean added, int number) {
		setLine(line,added,number);
	}
	/**
	 * Set the parameters (line, type, and location) of the modified line.
	 * @param line the textual line of code that has been modified (added or removed)
	 * @param added true if a new line, false if a removed line
	 * @param number the line number of the modification
	 */
	public void setLine(String line, boolean added, int number) {
		this.lineText = line;
		this.lineNumber = number;
		if(added)
			this.type = Type.Added;
		else
			this.type = Type.Deleted;
	}
	/**
	 * @return the modified line of code
	 */
	public String getLine() {
		return this.lineText;
	}
	/**
	 * @return the line number of the modified line of code
	 */
	public int getLineNumber() {
		return this.lineNumber;
	}
	/**
	 * @return true if the line was added, false otherwise
	 */
	public boolean isAddedLine() {
		return this.type == Type.Added;
	}
	
	/**
	 * @return true if the line was removed, false otherwise
	 */
	public boolean isDeleteLine() {
		return this.type == Type.Deleted;
	}

}
