package edu.cmu.phoenix.changecounter.test;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.phoenix.changecounter.Change;
import edu.cmu.phoenix.changecounter.Header;

public class TestHeader {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRead() {
		Header header = new Header();
		
		try {
			header.readHeader(new File("./test_data/Header.java"));
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
		
		Assert.assertEquals(2, header.getVersions().length);
		Assert.assertTrue(header.hasVersion(1));
		Assert.assertTrue(header.hasVersion(2));
		
		Change[] changes = header.getChanges(1);
		
		Assert.assertEquals(1, changes.length);
		
		Change change = changes[0];
		
		Assert.assertEquals(56, change.getNumber());
		Assert.assertEquals("Lorem Ipsum dolor sit amet, consectetuer adipiscing", change.getReason());
	}

}
