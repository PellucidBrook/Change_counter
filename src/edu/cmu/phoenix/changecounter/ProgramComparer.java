package edu.cmu.phoenix.changecounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import DiffLib.*;
import java.util.*;
import java.io.*;

public class ProgramComparer {
		
	public ArrayList<ModifiedLine> compare(Program prev, Program curr) throws IOException {
		
		String[] plist = readFile(prev.getFileName());
	    String[] clist = readFile(curr.getFileName());
	    
	    Report diffReport = new TextDiff().compare(plist, clist);
	    return convertReport(diffReport, plist, clist);
	}
	
	static ArrayList <ModifiedLine> convertReport(Report diffReport, String [] file1, String [] file2) {
		EditCommand temp;
		ArrayList <ModifiedLine> lines = new ArrayList<ModifiedLine>();
		String changetype;
		for (int i = 0; i < diffReport.size(); i++) {
			temp = (EditCommand) diffReport.get(i);
			changetype = temp.getCommand();
			
			if(changetype.equals("Match"))
				continue;
			else if(changetype.equals("Insert before"))
				addedLine(temp, lines, file2);
			else if(changetype.equals("Change")) {
				deletedLine(temp, lines, file1);
				addedLine(temp, lines, file2);
			}
			else if(changetype.equals("Delete"))
				deletedLine(temp, lines, file1);
			else if(changetype.equals("Append"))
				addedLine(temp, lines, file2);
		}
		
		return lines;				
	}
	
	static void addedLine(EditCommand temp, ArrayList <ModifiedLine> lines, String [] file2) {
		for (int j = 0; j <= temp.getNew().thruLineNum - temp.getNew().fromLineNum; j++) { //add
			lines.add( new ModifiedLine(file2[j + temp.getNew().fromLineNum], true, j + temp.getNew().fromLineNum + 1));
			//System.out.println("Added at Line " + (j + temp.getNew().fromLineNum + 1) + ": " + file2[j + temp.getNew().fromLineNum]);
		}
	}
	
	static void deletedLine(EditCommand temp, ArrayList <ModifiedLine> lines, String [] file1) {
		for (int j = 0; j <= temp.getOld().thruLineNum - temp.getOld().fromLineNum; j++) { //delete
			lines.add( new ModifiedLine(file1[j + temp.getOld().fromLineNum], false, j + temp.getOld().fromLineNum + 1));
			//System.out.println("Deleted Line " + (j + temp.getOld().fromLineNum + 1) + ": " + file1[j + temp.getOld().fromLineNum]);
		}
	}
	
	static String[] readFile(String file) throws IOException {
	    BufferedReader rdr = new BufferedReader(new FileReader(file));
	    Vector<String> s = new Vector<String>();
	    String line;
	    while ((line = rdr.readLine()) != null)
	    	s.addElement(line);
	    
	    String[] a = new String[s.size()];
	    s.copyInto(a);
	    rdr.close();
	    return a;
	  }
	
	

}
