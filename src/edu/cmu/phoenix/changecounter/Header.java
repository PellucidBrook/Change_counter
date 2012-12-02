package edu.cmu.phoenix.changecounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Header {
	private Map<Integer, List<Change>> versionChanges = new HashMap<Integer, List<Change>>();
	
	private int headerBeginLine;
	private int headerEndLine;
	
	private Pattern headerStartPattern = Pattern.compile("^[ \\t]*/\\*[ \\t]*Change History:[ \\t]*$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
	private Pattern versionPattern = Pattern.compile("^[ \\t]*\\*[ \\t]*Version[ \\t]*([\\d]+)[ \\t]*:[ \\t]*$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
	private Pattern changePattern = Pattern.compile("[ \\t]*\\*[\\t ]*-[ \\t]*Change[ \\t]*([\\d]*)[ \\t]*:([\\w\\d \\t$,./;\'\\[\\]{}:\"<>?~!@#$%^&\\*()`|\\\\]*)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
	private Pattern headerEndPattern = Pattern.compile("[ \\t]*\\*/[ \\t]*", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
	
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
	
	public void readHeader(InputStream stream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		
		boolean scanning = false;
		Integer currentVersion = null;
		
		String line;
		while ((line = reader.readLine()) != null)   {
			Matcher matcher;
			
			if(!scanning) {
				matcher = headerStartPattern.matcher(line);
				if(matcher.matches()) {
					scanning = true;
				}
			} else {
				matcher = headerEndPattern.matcher(line);
				if(matcher.matches()) {
					return;
				}
				
				matcher = versionPattern.matcher(line);
				if(matcher.matches()) {
					currentVersion = Integer.parseInt(matcher.group(1));
					addVersion(currentVersion);
				}
				
				matcher = changePattern.matcher(line);
				if(matcher.matches()) {
					Change change = new Change();
					change.setNumber(Integer.parseInt(matcher.group(1)));
					//change.setReason(Change.ReasonType.matcher.group(2));
					
					if(currentVersion != null) {
						addChange(currentVersion, change);
					}
				}
				
			}
		}
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
