package edu.cmu.phoenix.changecounter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Header {
	private Map<Integer, List<Change>> versionChanges = new HashMap<Integer, List<Change>>();
	
	private int headerBeginLine;
	private int headerEndLine;
	
	public void addVersion(Integer version) {
		if(!hasVersion(version)) {
			versionChanges.put(version, new ArrayList<Change>());
		}
	}
	
	public boolean hasVersion(Integer version) {
		return versionChanges.containsKey(version);
	}
	
	public void addChange(Integer version, Change change) {
		addVersion(version);		
		versionChanges.get(version).add(change);
	}
	
	public Integer[] getVersions() {		
		List<Integer> versions = new ArrayList<Integer>(versionChanges.keySet());
		Collections.sort(versions);
		Integer[] array = new Integer[versions.size()];
		return versions.toArray(array);
	}
	
	public Change[] getChanges(Integer version) {
		Change[] array = new Change[versionChanges.get(version).size()];
		return versionChanges.get(version).toArray(array);
	}
	
	public void readHeader(File file) throws IOException {
		InputStream stream = new FileInputStream(file);
		try {
			readHeader(stream);
		} finally {
			stream.close();
		}
	}
	
	public void readHeader(InputStream stream) {
		
	}
	
	
	public void writeHeader(File file) throws IOException {
		OutputStream stream = new FileOutputStream(file);
		try {
			writeHeader(stream);
		} finally {
			stream.close();
		}
	}
	
	public void writeHeader(OutputStream stream) {
		
	}

}
