package edu.cmu.phoenix.changecounter;

public class ModifiedLine {
	
	public static enum Type {
	    Added, Deleted 
	}
	
	private String lineText;
	private int lineNumber;
	private Type type;
	
	public ModifiedLine() {
		
	}
	public ModifiedLine(String line, boolean added, int number) {
		setLine(line,added,number);
	}
	public void setLine(String line, boolean added, int number) {
		this.lineText = line;
		this.lineNumber = number;
		if(added)
			this.type = Type.Added;
		else
			this.type = Type.Deleted;
	}
	public String getLine() {
		return this.lineText;
	}
	public int getLineNumber() {
		return this.lineNumber;
	}
	public boolean isAddedLine() {
		return this.type == Type.Added;
	}
	public boolean isDeleteLine() {
		return this.type == Type.Deleted;
	}

}
