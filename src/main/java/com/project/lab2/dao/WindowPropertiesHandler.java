package com.project.lab2.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class WindowPropertiesHandler {
	
	public static String mainWindow() {
		return getSoundUrl("main");
	}
	
	public static String alarmModifier() {
		return getSoundUrl("alarm_modifier");
	}
	
	public static String timerModifier() {
		return getSoundUrl("timer_modifier");
	}
	
	public static String error() {
		return getSoundUrl("error");
	}
	
	private static String getSoundUrl(String name) {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(new File(WindowPropertiesHandler.class.getResource("/properties/windows.properties").getFile())));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return p.getProperty(name);
	}
	
}
