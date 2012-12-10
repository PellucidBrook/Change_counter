package edu.cmu.phoenix.changecounter;
import java.io.*;

/****************************************
 *
 * OutputHandler class takes stream from program
 * and print it out to file.
 * @author Ethan Wang
 *
 ****************************************/

public class OutputHandler {

	public OutputHandler() {

	}

	public void writeChangeListing(Program newCL, String file) {
		String stringToWrite;
		Header headerToWrite;

		headerToWrite = newCL.getProgramHeader();
		stringToWrite = headerToWrite.render();
		
	    try {
	        FileOutputStream fout = new FileOutputStream(file);
	        PrintStream out = new PrintStream(fout);
	        out.println(stringToWrite);
	      } catch (IOException ex) {
	        System.out.println("There was a problem creating/writing to the temp file");
	        ex.printStackTrace();
	      }
		
		
		
	}

	/**
	 * Writes a source file with an updated header.
	 * 
	 * @param Program to write the updated source file for.
	 * @param Stream to write the updated source file to.
	 */
	public void writeAnnotatedSourceFile(Program newSF, OutputStream oStream) throws Exception {
		StringBuffer sourceFileData = new StringBuffer();

		// Read original file contents
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(newSF.getFileName()));		

			char[] buf = new char[1024];
			int numRead = 0;				

			while((numRead=reader.read(buf)) != -1) {
				String readData = String.valueOf(buf, 0, numRead);
				sourceFileData.append(readData);
			}
		} catch (IOException e) {
			throw new Exception("Unable to read original source file.", e);
		} finally {
			if(reader != null) {
				reader.close();
			}
		}

		// Write annotated source
		PrintStream writer = null;
		try {
			String annotatedSource = newSF.getProgramHeader().writeHeader(sourceFileData.toString());
			writer = new PrintStream(oStream);
			writer.print(annotatedSource);
		} catch (IOException e) {
			throw new Exception("Unable to write annotated source file.", e);
		} finally {
			if(writer != null) {
				writer.close();
			}
		}
	}

	public boolean closeIO (OutputStream oStream) {
		try {
			oStream.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}

