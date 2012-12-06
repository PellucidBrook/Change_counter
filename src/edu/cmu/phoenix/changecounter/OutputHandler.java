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

        public void writeChangeListing(Program newCL, OutputStream oStream) {
                String stringToWrite;
		Header headerToWrite;
		
		headerToWrite = newCL.getProgramHeader();
		stringToWrite = headerToWrite.render();
		try {
			oStream.writeChars(stringToWrite);
			oStream.flush();
			oStream.close();
                } catch (IOException e) {
			e.printStackTrace();
                } 
	}

	public void writeAnnotatedSourceFile(Program newSF, OutputStream oStream) {
                String stringToWrite;
                Header headerToWrite;
		
                headerToWrite = newCL.getProgramHeader();
		stringToWrite = newSF.contents; 
		try {
			oStream.writeChars(stringToWrite);
                        oStream.flush();
                        oStream.close();
		} catch (IOException e) {
			e.printStackTrace();
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

