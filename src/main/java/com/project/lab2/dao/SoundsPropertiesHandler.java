package com.project.lab2.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SoundsPropertiesHandler {

	public static String getDefaultSoundUrl() {
		return getSoundUrl("default");
	}
	
	public static String getSoundUrl(String name) {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(new File(SoundsPropertiesHandler.class.getResource("/properties/sounds.properties").getFile())));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return p.getProperty(name);
	}
	
}
