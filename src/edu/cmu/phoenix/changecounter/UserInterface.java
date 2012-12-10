package edu.cmu.phoenix.changecounter;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/****************************************
 * 
 * UserInterface class includes all information 
 * related to user input
 * 
 * @author Christine Parry
 *
 ****************************************/

/*	
 * Prompts user for change information. 
 */

public class UserInterface {


	private String userName;
	private int oldVersion;
	private int newVersion;
	private String oldFilePath;
	private String newFilePath;

	//	OutputInformation
	private String outputType;
	private String sourceFile;
	private String changeFile;
	private Date changeDate = new Date();

	public UserInterface() {

	}

	/*
	 * Constructor
	 */
	public UserInterface(int oldVersion, int newVersion, String oldFilePath, String newFilePath) {

	}


	/* 
	 * Called by main to prompt user for change and version
	 */
	public void executeInitialQuestions() {
		printOutputTopic("TSP CHANGE COUNTER");
		System.out.println("Welcome to the TSP Change Counter! Glad you could be here today.");
		System.out.println("You're going to need to files to compare and I'll ask you for additional information along the way");
		System.out.println("You can exit at any time by entering 'q'");

		printOutputTopic("GATHERING INITIAL INFORMATION");
		System.out.println("Let's not get ahead of ourselves though.");

		//		Get user name
		userName =  promptForUserName();

		//		Get file paths for old and new files
		System.out.println(" ");
		System.out.println("Okay " + userName + ", now I'm going to get some information about the two files you want to compare.");
		oldFilePath = promptForFilePath("What's the path for the old file?");
		newFilePath = promptForFilePath("What's the path for the new file?");

		//		Get version number of new file
		System.out.println(" ");
		System.out.println("Okay, Now I need the version number for the new file.");
		newVersion = promptForVersionNumber();
	}


	/* 
	 * Run in main. Begins sequence of saving change information to file.
	 */
	public void executeOutputMethod(Program newProgram) throws Exception {
		printOutputTopic("DEFINE OUTPUT TYPE");
		outputType = promptForOutputType();

		if (outputType.equals("c")) {
			promptForChangeOutput(newProgram);

		} else if (outputType.equals("s")) {
			promptForSourceOutput(newProgram);

		} else if (outputType.equals("b")) {
			promptForChangeOutput(newProgram);
			promptForSourceOutput(newProgram);
		} else {
			System.out.println("WHAT HAVE YOU DONE? You shouldn't be here! There must have been a problem getting the output type from the user");
		}
	}


	/* 
	 * Gets change log file from user to print output to and calls OutputHandler to do the printing.
	 */
	private void promptForChangeOutput(Program newProgram) throws FileNotFoundException {
		String changePrompt = "Enter file path to change listing: ";
		OutputHandler outputHandler = new OutputHandler();
		changeFile = promptForFilePath(changePrompt, true);
		//		final OutputStream outputStreamChangeFile;
		outputHandler.writeChangeListing(newProgram, changeFile);
	}


	/* 
	 * Gets source file from user to print output to and calls OutputHandler to do the printing.
	 */
	private void promptForSourceOutput(Program newProgram) {
		String sourcePrompt = "Enter file path to annotated source file: ";
		OutputHandler outputHandler = new OutputHandler();
		sourceFile = promptForFilePath(sourcePrompt, true);
		final OutputStream outputStreamSourceFile;
		try {
			outputStreamSourceFile = new FileOutputStream(sourceFile);
			outputHandler.writeAnnotatedSourceFile(newProgram, outputStreamSourceFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/* 
	 * Prompts user for user name
	 */
	private String promptForUserName() {
		String userName = getUserInput("What's your name, good lookin'?");
		System.out.println("What a nice name. I'm going to use that as your user name when we record the change information.");	
		return userName;
	}


	/* 
	 * Prompts user for version number.
	 * Only integer values are accepted for version number.
	 */
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


	/* Prompts user for file path. Only used for files to compare. 
	 * Will not create new file if it doesn't find one.
	 */
	private String promptForFilePath(String prompt) {
		return promptForFilePath(prompt, false);
	}


	/* Prompts user for file path. 
	 * If it's a file to compare, the program checks to see if that file exists. 
	 * If not, it prompts user again for a file path.
	 * If it's a change log or source file, 
	 * if the user enters a file that doesn't exist it will create that file and save to it. 
	 */
	private String promptForFilePath(String prompt, boolean createFile) {

		String filePath = null;
		while(true) {
			filePath = getUserInput(prompt);
			File file = new File(filePath);
			if(file.exists()) { 
				return filePath;
			} else {
				if(createFile) {
					try {
						file.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return filePath;
				} else {
					System.out.println("I can't find that file. Let's try again.");
				}
			}
		}

	}


	/*
	 * Prompts user for change information. 
	 * Prints out each modified line and asks user about the change 
	 */
	public ArrayList<Change> executeChangeAssignment(ArrayList<ModifiedLine> modifiedLines) {
		printOutputTopic("CHANGE INFORMATION");
		System.out.println("Let me get a little information about the changes you're making.");
		System.out.println("I'm going to print out each line of the new file that has been changed from the previous version.");
		System.out.println("And for each one, I'll ask you to assign a number to that change and the reason for the change.");
		System.out.println("You can assign multiple modified lines to a single change number, so if you enter the same change number for two different lines,");
		System.out.println("both of those lines will be associated with the same change number.");
		System.out.println("If you're in a rush, you can also assign all remaning modified lines to a single change number and reason.");
		System.out.println(" ");
		ArrayList<Change> changes = new ArrayList<Change>();

		String changePrompt = "Enter a change number for this line: ";

		printOutputTopic("MODIFIED LINES");
		for (ModifiedLine line : modifiedLines) {
			int oldChange = 0;
			{
				oldChange = 0;
				System.out.println("  ");
				System.out.println("    Line " + line.getLineNumber() + ":  " + line.getLine());

				int changeNum = getIntegerFromUser(changePrompt);

				for(Change change : changes) {
					if(change.getNumber() == changeNum) {
						System.out.println("You've used this change number before. I'll add this line to that change.");
						change.addLine(line);
						oldChange = 1;
						break; }
				}

				if (oldChange == 0) {
					System.out.println("You haven't used this change number before.");
					System.out.println("I'll need to get a reason for this change from you.");
					changes.add(createNewChange(changeNum));
				}
			}
		}
		return changes;	
	}


	/* 
	 * Creates new instance of change. 
	 * Called in executeChangeAssignment
	 */
	private Change createNewChange(int changeNum) {
		Change.ReasonType reason = getChangeReason();
		Change change = new Change(changeNum, userName, changeDate, reason);

		//		If the change type is "fix", the user needs to enter a defect number. 
		if (reason.equals(Change.ReasonType.Fix)) {
			System.out.println("Okay, you're committing a fix to a defect.");
			int defectNum = getIntegerFromUser("Enter a defect number (must be an integer):");
			change.setDefectNumber(defectNum);
		}

		return change;
	}

	/*
	 * Asks user for reason type. 
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

	/* 
	 * Asks user if they would like to save information 
	 * to a change log or source file. 
	 */
	public String promptForOutputType() {
		while(true) {
			String outputType = getUserInput("How would you like to save the output?: c (change listing), s (annotated source file), or b (both)?");
			outputType = outputType.toLowerCase();
			if (outputType.equals("c") || outputType.equals("s") || outputType.equals("b")) {
				return outputType;
			} else {
				System.out.println("Sorry, I don't recognize that change reason.");
			}
		}
	}


	/* 
	 * Used for change number and version number. 
	 * Makes sure that the value the user enters is an integer.
	 * If not, continues asking until valid number is entered. 
	 */
	private int getIntegerFromUser(String prompt) {
		String input = null;
		while(true) {
			input = getUserInput(prompt);

			try {
				int userNumber = Integer.parseInt(input);
				return userNumber;
			} catch (NumberFormatException n) {
				System.out.println(input + " not an integer!");
			}

		}

	}

	/* 
	 * Used as base method whenever the user interface asks for input from the user.
	 * Handles exceptions from getting user input.
	 * Makes sure input string is not null.
	 */
	private String getUserInput(String prompt) {
		System.out.print(prompt + " ");

		String inputLine = checkUserInput();

		if (inputLine.equalsIgnoreCase("quit")) {
			promptExitProgram();
		}

		return inputLine;
	}

	/* 
	 * Catches exceptions thrown by user input
	 */
	private String checkUserInput() {
		String inputLine = null;
		try {
			BufferedReader is = new BufferedReader(
					new InputStreamReader(System.in));
			inputLine = is.readLine();
			if (inputLine.length() == 0) return null;
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}

		if (inputLine.equalsIgnoreCase("q")) {
			promptExitProgram();
		}
		return inputLine;
	}

	/* 
	 * If a user enters 'q' at any time, they will be brought here, asking if they want to quit.
	 */
	private void promptExitProgram() {
		System.out.println("You've entered 'q', indicating you want to quit. Before we make any rash decisions, let me double check: ");

		while(true) {
			System.out.println("Are you sure you want to quit? Enter 'y' to quit, or 'n', to continue. ");
			String input = checkUserInput();

			if (input.equals("n")) {
				System.out.println("Alright, let's continue.");
				return;
			} else if (input.equals("y")){
				System.out.println("Well, I can't say I won't miss you.");
				System.exit(0);
			} else {
				System.out.println("Sorry, I don't recognize that. Let's try again.");
			}
		}
	}



	/* 
	 * Run at the end of main, asks user if she wants to compare another file or exit program.
	 */
	public void askToContinue() {
		System.out.println("Well, you've done it again, old friend. All your changes are recorded and you're good to go.");

		while(true) {
			String continueExit = getUserInput("Would you like to continue comparing files, or are you all done? Press 'c' to continue or 'q' to quit.");
			continueExit = continueExit.toLowerCase();
			if (continueExit.equals("c")) {
				System.out.println("Splendid! I could spend all day chatting with you.");
				return;
			} else if (continueExit.equals("q")){
				System.out.println("Well, I can't say I won't miss you.");
				System.exit(0);
				return;
			} else {
				System.out.println("Sorry, I don't recognize that. Let's try again.");
			}
		}
	}

	/* 
	 * Adds space between topics displayed to user.
	 */
	private void printOutputTopic(String topic) {
		System.out.println("  ");
		System.out.println("~~~~~~  " + topic + "  ~~~~~~");
	}


	/* 
	 * Getters for values called by main method. May be out of date.
	 */

	public int getOldVersion() {
		oldVersion = newVersion - 1;
		return oldVersion;
	}

	public int getNewVersion() {
		return newVersion;
	}

	public String getOldFilePath() {
		return oldFilePath;
	}

	public String getNewFilePath() {
		return newFilePath;
	}




}
