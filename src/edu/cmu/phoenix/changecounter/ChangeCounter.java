package edu.cmu.phoenix.changecounter;

import java.io.OutputStream;
import java.util.ArrayList;

public class ChangeCounter {

  public static void main(String [] args) {
		
	  UserInterface ui = new UserInterface();
	  ui.executeUserInterface();
		
	  // Get information from user for program 1 (old version)
	  
	  // Set information in Program class instance (oldProgram)
	  Program oldProgram = new Program();
	  oldProgram.setVersion(ui.getVersionNum());
	  oldProgram.setFileName(ui.getOldFilePath());
	  
	  // Get information from user for program 2 (new version)
	  
	  // Set information in Program class instance (newProgram)
	  Program newProgram = new Program();
	  newProgram.setVersion(ui.getVersionNum());
	  newProgram.setFileName(ui.getNewFilePath());
	  // Get change information from user
	  
	  // Send oldProgram and newProgram into ProgramComparer
	  ProgramComparer pc = new ProgramComparer();
	  ArrayList<ModifiedLine> = pc.compare(oldProgram, newProgram);
	  
	  
		
		
		int versionNum = ui.getVersionNum();
		int changeNum = ui.getChangeNum();
		Change change = ui.getChange();
		
		
		
		OutputHandler op = new OutputHandler();
		
		op.writeChangeListing(Program newCL, OutputStream oStream);
		op.writeAnnotatedSourceFile(Program newSF, OutputStream oStream);
		
		
		
	}
	
}
