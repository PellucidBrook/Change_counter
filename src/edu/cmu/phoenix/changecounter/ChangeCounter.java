package edu.cmu.phoenix.changecounter;

import java.io.OutputStream;
import java.util.ArrayList;

public class ChangeCounter {

	public static void main(String [] args) {


		while(true) {


			UserInterface userInterface = new UserInterface();
			userInterface.executeUserInterface();

			//	  Create oldVersion and newVersion of Program class
			Program oldProgram = new Program();
			oldProgram.setVersion(userInterface.getOldVersion());
			oldProgram.setFileName(userInterface.getOldFilePath());

			Program newProgram = new Program();
			newProgram.setVersion(userInterface.getNewVersion());
			newProgram.setFileName(userInterface.getNewFilePath());

			// Send oldProgram and newProgram into ProgramComparer
			ProgramComparer pc = new ProgramComparer();
			ArrayList<ModifiedLine> = pc.compare(oldProgram, newProgram);


			// Get change information from user






//			int versionNum = ui.getVersionNum();
//			int changeNum = ui.getChangeNum();
//			Change change = ui.getChange();








			//		Ask user if they want to write the output to the change listing or source file
			OutputHandler outputHandler = new OutputHandler();
			String outputType = ui.promptUserForOutputType();
			if (outputType.equals("c")) {
				outputHandler.writeChangeListing(newProgram, OutputStream oStream);
			} else if (outputType.equals("s")) {
				outputHandler.writeAnnotatedSourceFile(newProgram, OutputStream oStream);
			} 
			else {
				System.out.println("WHAT HAVE YOU DONE? You shouldn't be here!");
			}


		}

	}

}
