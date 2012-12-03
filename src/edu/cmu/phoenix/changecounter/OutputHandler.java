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
	private OutputStream oStream;
	
        public OutputHandler() {
		
        }

        public void writeChangeListing(Program newCL) {
                try {

                } catch (IOException e) {

                }
	}

	public void writeAnnotatedSourceFile(Program newSF) {
		try {
			
		} catch (IOException e) {

		} 
	}

	public boolean closeIO () {
	        try {
			.close();
			return true;
                } catch (IOException e) {
			return false;
                }
	}
}

