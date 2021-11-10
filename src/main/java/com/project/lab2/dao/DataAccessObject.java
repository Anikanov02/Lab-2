package com.project.lab2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.lab2.models.Alarm;
import com.project.lab2.models.Timer;

public class DataAccessObject {
	
	private static DataAccessObject dao = null;
	
	private DataAccessObject() {
		
	}
	
	public static DataAccessObject getInstance() {
		return dao==null?new DataAccessObject():dao;
	}
	
	public Connection getConnection(String connectionUrl) throws SQLException {   	
        return DriverManager.getConnection(connectionUrl);
    }
	
	public void insertTimer(Timer timer) {
		
	}
	
	public void insertAlarm(Alarm alarm) {
		
	}
	
	public List<Timer> getTimers(){
		return new ArrayList<Timer>();
	}
	
	public List<Alarm> getAlarms(){
		return new ArrayList<Alarm>();
	} 
	
}
