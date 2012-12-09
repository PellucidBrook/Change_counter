package edu.cmu.phoenix.changecounter;

//import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.OutputStream;
import java.util.ArrayList;

public class ChangeCounter {

	public static void main(String [] args) {


		while(true) {


			UserInterface userInterface = new UserInterface();
			userInterface.executeInitialQuestions();

			//	  Create oldVersion and newVersion of Program class
			Program oldProgram = new Program(userInterface.getOldVersion(), userInterface.getOldFilePath());
			Program newProgram = new Program(userInterface.getNewVersion(), userInterface.getNewFilePath());

			// Send oldProgram and newProgram into ProgramComparer
			ProgramComparer programComparer = new ProgramComparer();
			try {
				ArrayList<ModifiedLine> modifiedLine = programComparer.compare(oldProgram, newProgram);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Change change = userInterface.promptForChangeInfo();
			newProgram.addChange(change);

			//		Ask user if they want to write the output to the change listing or source file
			try {
				userInterface.executeOutputMethod(newProgram);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

	}

}
