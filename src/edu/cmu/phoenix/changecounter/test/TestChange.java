package edu.cmu.phoenix.changecounter.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.phoenix.changecounter.Change;
import edu.cmu.phoenix.changecounter.ModifiedLine;
import edu.cmu.phoenix.changecounter.Change.ReasonType;

/****************************************
 * 
 * TestChange includes tests for 
 * the Change class.
 * @author Jiyeon Lee
 *
 ****************************************/

public class TestChange {

	private static int SAMPLE_NUMBER = 10;
	private static String SAMPLE_USERNAME = "Sample Name";
	private static Date SAMPLE_DATE = new Date();
	private static ReasonType SAMPLE_REASON = Change.ReasonType.New;
	private static ModifiedLine SAMPLE_MODIFIEDLIE;
	private Change change;
	
	@Before
	public void setUp() throws Exception {		
		change = new Change(SAMPLE_NUMBER, SAMPLE_USERNAME, SAMPLE_DATE, SAMPLE_REASON);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testChange() {
		Change change= new Change();
		assertNotNull(change);
	}
	
	@Test
	public void testGetNumber() {
		assertEquals(SAMPLE_NUMBER, change.getNumber());
	}
	
	@Test
	public void testSetNumber() {
		
		// test values;
		int newNum = 20;
		
		change.setNumber(newNum);
		assertEquals(newNum, change.getNumber());
	}
	
	@Test
	public void testGetUserName() {
		assertEquals(SAMPLE_USERNAME, change.getUserName());
	}
	
	@Test
	public void testSetUserName() {
		// test values;
		String newUserName = "NEW NAME";
		
		change.setUserName(newUserName);
		assertEquals(newUserName, change.getUserName());
	}
	
	@Test
	public void testGetChangeDate() {
		assertEquals(SAMPLE_DATE, change.getChangeDate());
	}
	
	@Test
	public void testSetChangeDate() {
		// test value
		Date newDate = new Date();
		
		// test setup
		change.setChangeDate(newDate);
		
		// run test
		assertEquals(newDate, change.getChangeDate());
	}
	
	@Test
	public void testGetDefectNumber() {

		// test setup
		int newDefectNum = 10;
		change.setDefectNumber(newDefectNum);
		
		// run test
		assertEquals(newDefectNum, change.getDefectNumber());
	}
	
	@Test
	public void testSetDefectNumber() {
		// test value
		int newDefectNum = 20;
		
		// test setup
		change.setDefectNumber(newDefectNum);
		
		// run test
		assertEquals(newDefectNum, change.getDefectNumber());
	}
	
	@Test
	public void tesGetReason() {
		assertEquals(SAMPLE_REASON, change.getReason());
	}
	
	@Test
	public void testSetReason() {
		// test value
		ReasonType newType = Change.ReasonType.Enhancement;
		
		// test setup
		change.setReason(newType);
		
		// run test
		assertEquals(newType, change.getReason());
	}
	
	@Test
	public void testAddAddedLine() {
		
		// test values
		String SAMPLE_LINE = "a = 2+2;";
		int SAMPLE_LINE_NUMBER = 12;
		boolean SAMPLE_ADDED = true;
		int initialAddedLOC = change.getAddedLOC();
		
		// test setup
		SAMPLE_MODIFIEDLIE = new ModifiedLine(SAMPLE_LINE,SAMPLE_ADDED,SAMPLE_LINE_NUMBER);
		change.addLine(SAMPLE_MODIFIEDLIE);
		
		// run test
		assertTrue(change.getLines().contains(SAMPLE_MODIFIEDLIE));
		assertEquals(initialAddedLOC + 1, change.getAddedLOC());
	}
	
	@Test
	public void testAddDeletedLine() {
		
		// test values
		String SAMPLE_LINE = "a = 2+2;";
		int SAMPLE_LINE_NUMBER = 12;
		boolean SAMPLE_ADDED = false;
		int initialDeletedLOC = change.getDeletedLOC();
		
		// test setup
		SAMPLE_MODIFIEDLIE = new ModifiedLine(SAMPLE_LINE,SAMPLE_ADDED,SAMPLE_LINE_NUMBER);
		change.addLine(SAMPLE_MODIFIEDLIE);
		
		// run test
		assertTrue(change.getLines().contains(SAMPLE_MODIFIEDLIE));
		assertEquals(initialDeletedLOC + 1, change.getDeletedLOC());
	}
	
	@Test
	public void testGetLines() {
		
		// test values
		String SAMPLE_LINE = "a = 2+2;";
		int SAMPLE_LINE_NUMBER = 12;
		boolean SAMPLE_ADDED = false;
		ModifiedLine SAMPLE_MODIFIEDLIE1 = new ModifiedLine(SAMPLE_LINE,SAMPLE_ADDED,SAMPLE_LINE_NUMBER);
		
		SAMPLE_LINE = "b = 3+3;";
		SAMPLE_LINE_NUMBER = 13;
		SAMPLE_ADDED = true;
		ModifiedLine SAMPLE_MODIFIEDLIE2 = new ModifiedLine(SAMPLE_LINE,SAMPLE_ADDED,SAMPLE_LINE_NUMBER);

		// test setup
		List<ModifiedLine> SAMPLE_LINES = new ArrayList<ModifiedLine>();
		SAMPLE_LINES.add(SAMPLE_MODIFIEDLIE1);
		SAMPLE_LINES.add(SAMPLE_MODIFIEDLIE2);
		
		change.addLine(SAMPLE_MODIFIEDLIE1);
		change.addLine(SAMPLE_MODIFIEDLIE2);
				
		// run test
		assertEquals(SAMPLE_LINES, change.getLines());
	}
	
	@Test
	public void testGetAddedLOC() {
		// test values
		String SAMPLE_LINE = "a = 2+2;";
		int SAMPLE_LINE_NUMBER = 12;
		boolean SAMPLE_ADDED = true;
		
		// test setup
		SAMPLE_MODIFIEDLIE = new ModifiedLine(SAMPLE_LINE,SAMPLE_ADDED,SAMPLE_LINE_NUMBER);
		change.addLine(SAMPLE_MODIFIEDLIE);
		
		// run test
		assertEquals(1, change.getAddedLOC());
	}
	
	@Test
	public void testGetDeletedLOC() {
		// test values
		String SAMPLE_LINE = "a = 2+2;";
		int SAMPLE_LINE_NUMBER = 12;
		boolean SAMPLE_ADDED = false;
		
		// test setup
		SAMPLE_MODIFIEDLIE = new ModifiedLine(SAMPLE_LINE,SAMPLE_ADDED,SAMPLE_LINE_NUMBER);
		change.addLine(SAMPLE_MODIFIEDLIE);
		
		// run test
		assertEquals(1, change.getDeletedLOC());
	}
}
