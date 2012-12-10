package edu.cmu.phoenix.changecounter;

//import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.OutputStream;
import java.util.ArrayList;

public class ChangeCounter {

	public static void main(String [] args) throws IOException {


		while(true) {


			UserInterface userInterface = new UserInterface();
			userInterface.executeInitialQuestions();

			//	  Create oldVersion and newVersion of Program class
			Program oldProgram = new Program(userInterface.getOldVersion(), userInterface.getOldFilePath());
			Program newProgram = new Program(userInterface.getNewVersion(), userInterface.getNewFilePath());

			// Send oldProgram and newProgram into ProgramComparer
			ProgramComparer programComparer = new ProgramComparer();

			ArrayList<ModifiedLine> modifiedLine = programComparer.compare(oldProgram, newProgram);

			ArrayList<Change> changes = userInterface.executeChangeAssignment(modifiedLine);
			for (Change change : changes) {
				{
					newProgram.addChange(change);
				}
			}

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
