package edu.cmu.phoenix.changecounter.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.phoenix.changecounter.ModifiedLine;

/****************************************
 * 
 * TestModifiedLine includes tests for 
 * the ModifiedLine class.
 * @author Daniel Souza <souzad@cmu.edu>
 *
 ****************************************/

public class TestModifiedLine {

	private static String SAMPLE_LINE = "a = 2+2;";
	private static int SAMPLE_LINE_NUMBER = 12;
	private static boolean SAMPLE_ADDED = true;
	private ModifiedLine modifiedLine;
	@Before
	public void setUp() throws Exception {
		modifiedLine = new ModifiedLine(SAMPLE_LINE,SAMPLE_ADDED,SAMPLE_LINE_NUMBER);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testModifiedLine() {
		ModifiedLine line= new ModifiedLine();
	}

	@Test
	public void testModifiedLineStringBooleanInt() {
		ModifiedLine line = new ModifiedLine(SAMPLE_LINE,SAMPLE_ADDED,SAMPLE_LINE_NUMBER);
		assertEquals(line.getLine(),SAMPLE_LINE);
		assertEquals(line.getLineNumber(),SAMPLE_LINE_NUMBER);
		assertEquals(line.isAddedLine(),SAMPLE_ADDED);
	}

	@Test
	public void testSetLine() {
		ModifiedLine line = new ModifiedLine(SAMPLE_LINE,SAMPLE_ADDED,SAMPLE_LINE_NUMBER);
		assertEquals(line.getLine(),SAMPLE_LINE);
		assertEquals(line.getLineNumber(),SAMPLE_LINE_NUMBER);
		assertEquals(line.isAddedLine(),SAMPLE_ADDED);
	}

	@Test
	public void testGetLine() {
		assertEquals(modifiedLine.getLine(),SAMPLE_LINE);
	}

	@Test
	public void testGetLineNumber() {
		assertEquals(modifiedLine.getLineNumber(),SAMPLE_LINE_NUMBER);
	}

	@Test
	public void testIsAddedLine() {
		assertEquals(modifiedLine.isAddedLine(),SAMPLE_ADDED);
	}

	@Test
	public void testIsDeletedLine() {
		assertEquals(modifiedLine.isDeleteLine(),!SAMPLE_ADDED);
	}

}
