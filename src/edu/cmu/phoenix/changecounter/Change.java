package edu.cmu.phoenix.changecounter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/****************************************
 * 
 * Change class includes all information 
 * related to a specific change of the program.
 * @author Jiyeon Lee
 *
 ****************************************/

public class Change {
	
	// type of the reason of the change. 
	public static enum ReasonType {
	    Fix, Enhancement, New
	}
	
	private int number;	// change number - this value can be used as a ID.
	private String userName;	// the user's name who changed the program.
	private Date changeDate;	// changed date
	private ReasonType reason;	// the reason of the change. It is one of 'Fix', 'Enhancement', and 'New'.
	private int defectNumber;	// the defect number related to this change. It will have a value when the reason is 'Fix'.
	private List<ModifiedLine> modifiedLines;	// keeps all the modified lines from this change.
	private int addedLOC;	// count of the added lines
	private int deletedLOC;	// count of the deleted lines
	
	/*
	 * constructor with no initial values
	 */
	public Change() {
		modifiedLines = new ArrayList<ModifiedLine>();
	}
	
	/*
	 * constructor with initial values.
	 */
	public Change(int number, String userName, Date changeDate, ReasonType reason) {
		this.number = number;
		this.userName = userName;
		this.changeDate = changeDate;
		this.reason = reason;
		
		modifiedLines = new ArrayList<ModifiedLine>();
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Date getChangeDate() {
		return changeDate;
	}
	
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	
	public int getDefectNumber() {
		return defectNumber;
	}
	
	public void setDefectNumber(int defectNumber) {
		this.defectNumber = defectNumber;
	}
	
	public ReasonType getReason() {
		return reason;
	}
	
	public void setReason(ReasonType reason) {
		this.reason = reason;
	}
	
	public void addLine(ModifiedLine modifiedLine) {
		
		modifiedLines.add(modifiedLine);
		
		// Based on the type of the modification,
		// increase the count of added or deleted lines.
		if (modifiedLine.isAddedLine()) {
			addedLOC++;
		}
		else {
			deletedLOC++;
		}
	}
	
	public List<ModifiedLine> getLines() {
		return modifiedLines;
	}
	
	public int getAddedLOC() {
		return addedLOC;
	}
	
	public int getDeletedLOC() {
		return deletedLOC;
	}
}
