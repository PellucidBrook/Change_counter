package edu.cmu.phoenix.changecounter.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.phoenix.changecounter.Program;


public class TestProgram {
	private Program program;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetFileName() {
		String filePath= "C:/Users/Documents/JavaJ2EE/Shape.java";
		String expectedFileName= "Shape.java";
		
		program.setFileName(filePath);
		String actualFileName= program.getFileName();
		
		assertEquals(expectedFileName, actualFileName);
	}

}
