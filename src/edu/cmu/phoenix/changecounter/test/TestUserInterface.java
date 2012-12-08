/**
 * 
 */
package edu.cmu.phoenix.changecounter.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.cmu.phoenix.changecounter.Change;
import edu.cmu.phoenix.changecounter.UserInterface;

/**
 * @author Christine Parry
 *
 */
public class TestUserInterface {


	
	private static Change change;
	private static int SAMPLE_OLD_VERSION = 1;
	private static int SAMPLE_NEW_VERSION = 2;
	private static String SAMPLE_OLD_FILE_PATH = "aa/bb.txt";
	private static String SAMPLE_NEW_FILE_PATH = "cc/dd.txt";
	private static int SAMPLE_CHANGE = 3;
	private static String prompt = "Generic prompt";
	private UserInterface userInterface;
	

	
	@Before
	public void setUp() throws Exception {
		userInterface = new UserInterface();
	}
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUserInterface() {
		userInterface = new UserInterface();
	}

	public void testGetOldVersion() {
		assertEquals(SAMPLE_OLD_VERSION, userInterface.getOldVersion());
	}
	
	
	
	
	
}
