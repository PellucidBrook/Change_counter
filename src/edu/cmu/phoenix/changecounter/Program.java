package edu.cmu.phoenix.changecounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/****************************************
 * 
 * Program class is a representation of each version of  
 * the code which the user submits, to the change counter.
 * @author Deebika Muthukumarasamy
 *
 ****************************************/

public class Program {
	private int    version;
	private String fileName;
	private Change addedChange;
	private int    totalLOC;
	private Header programHeader;
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String newFilePath) {
		String[] filePath = newFilePath.split("/");
		String file = filePath[filePath.length-1];
		//System.out.println(filePath[filePath.length-1]);
		
		this.fileName = file;
	}
	
	public Change getAddedChange() {
		return addedChange;
	}
	public void setAddedChange(Change addedChange) {
		this.addedChange = addedChange;
	}
	
	public int getTotalLOC(File givenFilePath) throws IOException {
		FileReader fileReader=new FileReader(givenFilePath);
		BufferedReader lineReader=new BufferedReader(fileReader);
		totalLOC=0;
		boolean isEOF=false;
		
		do{
			String line=lineReader.readLine();
			if(line!=null){
				isEOF=true;
				line=line.replaceAll("\\n|\\t|\\s", "");
				
				if((!line.equals("")) && (!line.startsWith("//"))) {
					totalLOC = totalLOC + 1;
				}
			} else {
				isEOF=false;
			}
		}while(isEOF);
		
		lineReader.close();
		fileReader.close();
		
		return totalLOC;
	}
	public void setTotalLOC(int totalLOC) {
		this.totalLOC = totalLOC;
	}
	
	public Header getProgramHeader() {
		return programHeader;
	}
	public void setProgramHeader(Header programHeader) {
		this.programHeader = programHeader;
	}
	
}
