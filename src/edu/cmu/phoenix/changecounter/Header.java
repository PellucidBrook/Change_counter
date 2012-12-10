package edu.cmu.phoenix.changecounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/****************************************
 * 
 * The Header class maintains a programs version and change
 * history in the header of a source file. It's primary
 * roles are to parse the version and change data from
 * an existing source file and to render the version and
 * change data in the class into a header comment. 
 * 
 * @author Micah Lee <mtl@cmu.edu> 
 *
 ****************************************/

public class Header {
	private Map<Integer, List<Change>> versionChanges = new HashMap<Integer, List<Change>>();
	
	// Match the pattern: /* Change History:
	private Pattern headerStartPattern = Pattern.compile("^[ \\t]*/\\*[ \\t]*Change History:[ \\t]*$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
	// Match the pattern: * Version <version_num>:
	private Pattern versionPattern = Pattern.compile("^[ \\t]*\\*[ \\t]*Version[ \\t]*([\\d]+)[ \\t]*:[ \\t]*$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
	// Match the pattern: *  - Change <change_num>: <change_reason> 
	private Pattern changePattern = Pattern.compile("[ \\t]*\\*[\\t ]*-[ \\t]*Change[ \\t]*([\\d]*)[ \\t]*:([\\w\\d \\t$,./;\'\\[\\]{}:\"<>?~!@#$%^&\\*()`|\\\\]*)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
	// Match the pattern: *  - Change <change_num>: Fix[<defect_num>] 
	private Pattern changeFixPattern = Pattern.compile("[ \\t]*\\*[\\t ]*-[ \\t]*Change[ \\t]*([\\d]*)[ \\t]*:[ \\t]*(Fix)\\[([0-9]+)\\][ \\t]*", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
	// Match the pattern: */
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
	
	/**
	 * Initializes the class by parsing an existing source file.
	 */
	public void readHeader(File file) throws IOException {
		InputStream stream = new FileInputStream(file);
		try {
			readHeader(stream);
		} finally {
			stream.close();
		}
	}
	
	/**
	 * Initializes the class by parsing an existing source file.
	 */
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
					continue;
				}
				
				matcher = changeFixPattern.matcher(line);
				
				if(matcher.matches()) {
					Change change = new Change();
					change.setNumber(Integer.parseInt(matcher.group(1)));
					change.setReason(Change.ReasonType.valueOf(matcher.group(2).trim()));
					change.setDefectNumber(Integer.parseInt(matcher.group(3)));					
					
					if(currentVersion != null) {
						addChange(currentVersion, change);
					}
					continue;
				}
				
				matcher = changePattern.matcher(line);
				if(matcher.matches()) {
					Change change = new Change();
					change.setNumber(Integer.parseInt(matcher.group(1)));
					change.setReason(Change.ReasonType.valueOf(matcher.group(2).trim()));
					
					if(currentVersion != null) {
						addChange(currentVersion, change);
					}
					continue;
				}
				
			}
		}
	}
	
	/**
	 * Updates a source code listing with the current change header.
	 * Either replaces an existing header or adds an initial header.
	 * @param contents Source code listing to update
	 * @return Updated source code listing
	 */
	public String writeHeader(String contents) throws IOException {
		Integer headerStart = findHeaderStart(contents);
		Integer headerEnd = findHeaderEnd(headerStart, contents);
		
		StringBuilder result = new StringBuilder();
		
		if(headerDoesExist(headerStart, headerEnd)) {
			result.append(contents.substring(0, headerStart));
			result.append(render());
			result.append(contents.substring(headerEnd, contents.length()-1));
		} else {
			result.append(render());
			result.append(System.getProperty("line.separator"));
			result.append(contents);
		}
		
		return result.toString();
	}
	
	private boolean headerDoesExist(Integer start, Integer end) {
		return start != null && end != null;
	}
	
	private Integer findHeaderStart(String contents) {		
		BufferedReader reader = new BufferedReader(new StringReader(contents));

		int loc = 0;
		
		try{
			String line;
			while ((line = reader.readLine()) != null)   {
				Matcher match = headerStartPattern.matcher(line);
				if(match.matches()) {
					return loc + match.start();
				}
				
				loc += line.length() + 1; // Keep track of the current source position, including the newline character
			}
			reader.close();
		} catch (Exception e) {
			return null;
		}
		
		return null;
	}
	
	private Integer findHeaderEnd(Integer headerStart, String contents) {
		if(headerStart == null) {
			return null;
		}
		
		BufferedReader reader = new BufferedReader(new StringReader(contents.substring(headerStart)));

		int loc = 0;
		
		try{
			String line;
			while ((line = reader.readLine()) != null)   {
				Matcher match = headerEndPattern.matcher(line);
				if(match.matches()) {
					return loc + match.end();
				}
				
				loc += line.length() + 1; // Keep track of the current source position, including the newline character
			}
			reader.close();
		} catch (Exception e) {
			return null;
		}
		
		return null;
	}
	
	
	/***
	 * Renders the class content into a comment header
	 */
	public String render() {
		StringBuilder result = new StringBuilder();
		
		String nl = System.getProperty("line.separator");
		
		result.append("/* Change History:");
		result.append(nl);
		
		for(Integer version: getVersions()) {
			result.append(String.format("* Version %d:", version));
			result.append(nl);
			
			for(Change change: getChanges(version)) {
				if(change.getReason() == Change.ReasonType.Fix) {
					result.append(String.format("*  - Change %d: %s[%d]", change.getNumber(), change.getReason().toString(), change.getDefectNumber()));
				} else {
					result.append(String.format("*  - Change %d: %s", change.getNumber(), change.getReason().toString()));	
				}
				
				result.append(nl);
			}
			
			result.append("*");
			result.append(nl);
		}
		
		result.append("*/");		
		
		return result.toString();
	}
}
