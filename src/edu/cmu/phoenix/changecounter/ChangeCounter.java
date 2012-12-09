package edu.cmu.phoenix.changecounter;

import java.io.OutputStream;
import java.util.ArrayList;

public class ChangeCounter {

  public static void main(String [] args) {
		
	  
	  while(true) {
	  
	  
	  UserInterface userInterface = new UserInterface();
	  userInterface.executeUserInterface();
		
	  int newVersionNum = userInterface.getVersionNum();
	  int oldVersionNum = newVersionNum - 1;
	  
	  
	  // Get information from user for program 1 (old version)
	  
	  // Set information in Program class instance (oldProgram)
	  Program oldVersion = new Program();
	  oldProgram.setVersion(userInterface.getVersionNum());
	  oldProgram.setFileName(userInterface.getOldFilePath());
	  
	  // Get information from user for program 2 (new version)
	  
	  // Set information in Program class instance (newProgram)
	  Program newVersion = new Program();
	  newProgram.setVersion(userInterface.getVersionNum());
	  newProgram.setFileName(userInterface.getNewFilePath());
	  // Get change information from user
	  
	  // Send oldProgram and newProgram into ProgramComparer
	  ProgramComparer pc = new ProgramComparer();
	  ArrayList<ModifiedLine> = pc.compare(oldVersion, newVersion);
	  
	  
		
		
		int versionNum = ui.getVersionNum();
		int changeNum = ui.getChangeNum();
		Change change = ui.getChange();
		
		
		
		
		
		
		
		
//		Ask user if they want to write the output to the change listing or source file
		OutputHandler outputHandler = new OutputHandler();
		String outputType = ui.promptUserForOutputType();
		if (outputType.equals("c")) {
			outputHandler.writeChangeListing(newVersion, OutputStream oStream);
		} else if (outputType.equals("s")) {
			outputHandler.writeAnnotatedSourceFile(newVersion, OutputStream oStream);
		} 
		else {
			System.out.println("WHAT HAVE YOU DONE? You shouldn't be here!");
		}
		
		
	  }
		
	}
	
}
