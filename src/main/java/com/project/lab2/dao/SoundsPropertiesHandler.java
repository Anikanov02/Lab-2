package com.project.lab2.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SoundsPropertiesHandler {

	public static String getDefaultSoundUrl() {
		return getSoundUrl("default");
	}
	
	public static String getSoundUrl(String name) {
		return getProperties().getProperty(name);
	}
	
	public static List<String> getSoundOptions() {
		List<String> keys = new ArrayList<>();
		getProperties().keySet().stream().forEach((n)->{
			keys.add(n.toString());
		});
		return keys;
	}
	
	private static Properties getProperties() {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(new File(SoundsPropertiesHandler.class.getResource("/properties/sounds.properties").getFile())));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return p;
	}
	
}
