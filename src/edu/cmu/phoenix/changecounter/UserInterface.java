package edu.cmu.phoenix.changecounter;

import java.io.*;
import java.util.Date;

/****************************************
 * 
 * UserInterface class includes all information 
 * related to user input
 * 
 * @author Christine Parry
 *
 ****************************************/

/*	Prompts user for change information. 
 * The system design called for a separate method to prompt the user for the change number
 * outside of promptForChangeInfo. I've kept it that way, though I don't fully understand why we're doing it that way. 
 * So for now, we call promptForChangeNumber in promptForChangeInfo, and changeNum is accessible to main in two ways:
 * 1. Call getChange, which returns the change instance created in executeUserInterface
 * 2. Call getChangeNum, which returns only the change number. 
 */

public class UserInterface {

	public static enum OutputType {
		ChangeListing, SourceFile
	}


	private Change change;
	private int oldVersion;
	private int newVersion;
	private String oldFilePath;
	private String newFilePath;
	private int changeNum;

	public UserInterface() {

	}

	public UserInterface(int oldVersion, int newVersion, String oldFilePath, String newFilePath) {

	}

	//	Called by main to prompt user for change and version
	public UserInterface executeUserInterface() {
		System.out.println("Welcome to the TSP Change Counter! Glad you could be here today.");
		System.out.println("Let's not get ahead of ourselves though. What's your name?");
		
		
		
		
		
		System.out.println("First, I'll need the version number and file path for the old file.");
		oldVersion = promptForVersionNumber();
		oldFilePath = promptForFilePath();

		System.out.println("Okay, Now I need the version number and file path for the new file.");
		newVersion = promptForVersionNumber();
		newFilePath = promptForFilePath();

		changeNum = promptForChangeNumber();
		change = promptForChangeInfo();

		UserInterface userInterface = new UserInterface(oldVersion, newVersion, oldFilePath, newFilePath);
		return userInterface;
	}



	//	Prompts user for version number. Only integer values are accepted for version number.
	private int promptForVersionNumber() {
		while(true) {
			int newVersion = getIntegerFromUser("Enter a version number for the new version: (Must be an integer greater than zero)");
			if (newVersion < 1) {
				System.out.println("Version Number: " + newVersion + " not greater than zero.");
			} else {
				return newVersion;
			}
		}
	}


	//	Prompts user for change number. Only integer values are accepted for change number.
	private int promptForChangeNumber() {
		int changeNumber = getIntegerFromUser("Enter a change number: ");
		return changeNumber;
	}

	//	Prompts user for old file path. Currently accepts any string as valid file path.
	private String promptForFilePath() {
		String filePath = getUserInput("Enter path for file: ");
		return filePath;
	}


	/*	Prompts user for change information. 
	 */
	private Change promptForChangeInfo() {

		System.out.println("Let's get a little information about the change you're looking at.");
		String userName = getUserInput("Enter user name: ");
		Date changeDate = new Date();
		Change.ReasonType reason = getChangeReason();

		return new Change(changeNum, userName, changeDate, reason);
	}

	/*	Asks user for reason type. 
	 * Checks to make sure it's one of three valid strings: f, e, or n. 
	 * If not, repeats until a valid string is entered
	 */
	private Change.ReasonType getChangeReason() {
		while(true) {
			String reasonString = getUserInput("Enter a reason for this change: f (fix defect), e (enhancement), or n (new program)");
			reasonString = reasonString.toLowerCase();

			if (reasonString.equals("f")) {
				return Change.ReasonType.Fix;
			} else if (reasonString.equals("e")) {
				return Change.ReasonType.Enhancement;
			} else if (reasonString.equals("n")) {
				return Change.ReasonType.New;}
			else {
				System.out.println("Sorry, I don't recognize that change reason.");
			}
		}
	}


	public String promptUserForOutputType() {
		while(true) {
			String outputType = getUserInput("How would you like to save the output?: c (change listing), or s (source file)");
			outputType = outputType.toLowerCase();

			if (outputType.equals("c") || outputType.equals("s")) {
				return outputType;
			} else {
				System.out.println("Sorry, I don't recognize that change reason.");
			}
		}
	}



	/* Used for change number and version number. 
	 * Makes sure that the value the user enters is an integer.
	 * If not, continues asking until valid number is entered. 
	 */
	private int getIntegerFromUser(String prompt) {
		while(true) {
			String input = getUserInput(prompt);

			try {
				int userNumber = Integer.parseInt(input);
				return userNumber;
			} catch (NumberFormatException n) {
				System.out.println(input + " not an integer!");
				input = getUserInput(prompt);
			}
		}

	}

	/* Used as base method whenever the user interface asks for input from the user.
	 * Handles exceptions from getting user input.
	 * Makes sure input string is not null.
	 */
	private String getUserInput(String prompt) {
		String inputLine = null;
		System.out.print(prompt + " ");
		try {
			BufferedReader is = new BufferedReader(
					new InputStreamReader(System.in));
			inputLine = is.readLine();
			if (inputLine.length() == 0) return null;
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
		return inputLine;
	}


	// Getters for values called by main method
	public Change getChange() {
		return change;
	}

	public int getOldVersion() {
		oldVersion = newVersion - 1;
		return oldVersion;
	}

	public int getNewVersion() {
		return newVersion;
	}

	public int getChangeNum() {
		return changeNum;
	}

	public String getOldFilePath() {
		return oldFilePath;
	}

	public String getNewFilePath() {
		return newFilePath;
	}
	

}
