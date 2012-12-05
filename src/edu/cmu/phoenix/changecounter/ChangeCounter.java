package edu.cmu.phoenix.changecounter;

public class ChangeCounter {

  public static void main(String [] args) {
		
		UserInterface ui = new UserInterface();
		
		ui.executeUserInterface();
		int versionNum = ui.getVersionNum();
		int changeNum = ui.getChangeNum();
		Change change = ui.getChange();
		String oldFilePath = ui.getOldFilePath();
		String newFilePathString = ui.getNewFilePath();
		
	}
	
}
