
import java.io.*;
import java.util.Date;


public class UserInterface {

	
	public int promptForVersionNumber() {
		int versionNumber = getIntegerFromUser("Enter a version number: ");
		return versionNumber;
	}
	
	public int promptForChangeNumber() {
		int changeNumber = getIntegerFromUser("Enter a change number: ");
		return changeNumber;
	}

	public String promptForOldFilePath() {
		String oldFilePath = getUserInput("Enter path for old file: ");
		return oldFilePath;
	}
	
	public String promptForNewFilePath() {
		String newFilePath = getUserInput("Enter path for new file: ");
		return newFilePath;
	}
	
	public Change promptForChangeInfo() {
		
		 Change change = new Change();
		 
		 System.out.println("Let's get a little information about the change you're looking at.");
		 
		 String userName = getUserInput("Enter user name: ");
		 change.setUserName(userName);
		 
		 Date changeDate = new Date();
		 change.setChangeDate(changeDate);
		 
		 String reasonString = getChangeReason();
		 if (reasonString.equals("f")) {
			 change.setReason(Change.ReasonType.Fix);
			 } else if (reasonString.equals("e")) {
				 change.setReason(Change.ReasonType.Enhancement);
			 } else if (reasonString.equals("n")) {
				 change.setReason(Change.ReasonType.New); 
		 }
		 return change;
	}
	
	public String getChangeReason() {
		
		boolean validInput = false;
		String reason = null;
		
		while(validInput == false) {
			reason = getUserInput("Enter a reason for this change: f (fix defect), e (enhancement), or n (new program)");
			if (reason.equals("f") || reason.equals("e") || reason.equals("n")) {
				System.out.println("Sorry, I don't recognize that change reason.");
				validInput = true;
			}	
		}
		return reason;
	}
	
	
	public int getIntegerFromUser(String prompt) {

		boolean validInput = false;
		int userNumber = 0;
		String input = getUserInput(prompt);

		while(validInput == false) {
			try {
				userNumber = Integer.parseInt(input);
				validInput = true;
			} catch (NumberFormatException n) {
				System.out.println(input + " not an integer!");
				input = getUserInput(prompt);
			}
		}
		return userNumber;
	}
	

	//	Handles exceptions from getting user input
	public String getUserInput(String prompt) {
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

}
