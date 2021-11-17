package com.project.lab2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.lab2.models.Alarm;
import com.project.lab2.models.Timer;

public class DataAccessObject {
	
	private static DataAccessObject dao = null;
	public static final String URL="jdbc:mysql://localhost:3306/lab2_db?serverTimezone=UTC";
	public static final String USER="root";
	public static final String PASSWORD="1i8ns9";
	private DataAccessObject() {
		
	}
	
	public static DataAccessObject getInstance() {
		return dao==null?new DataAccessObject():dao;
	}
	
	public Connection getConnection(String URL) throws SQLException {   	
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
	
	public void insertTimer(Timer timer) {
		String query = "insert into timers (hr,min,sec,sound) values (?,?,?,?);";
		try(PreparedStatement statement = getConnection(URL).prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1, timer.getHr());
			statement.setInt(2, timer.getMin());
			statement.setInt(3, timer.getSec());
			statement.setString(4, timer.getSound());
			statement.executeUpdate();
			try(ResultSet rs = statement.getGeneratedKeys()){
        		if (rs.next()){
        			timer.setId(rs.getInt(1));
        		}
        	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertAlarm(Alarm alarm) {
		String query = "insert into alarms (hr,min,sound) values (?,?,?);";
		try(PreparedStatement statement = getConnection(URL).prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1, alarm.getHr());
			statement.setInt(2, alarm.getMin());
			statement.setString(3, alarm.getSound());
			statement.executeUpdate();
			try(ResultSet rs = statement.getGeneratedKeys()){
        		if (rs.next()){
        			alarm.setId(rs.getInt(1));
        		}
        	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeTimer(Timer timer) {
		String query = "delete from timers where id = ?;";
		try (PreparedStatement statement = getConnection(URL).prepareStatement(query)){
			statement.setInt(1, timer.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeAlarm(Alarm alarm) {
		String query = "delete from alarms where id = ?;";
		try (PreparedStatement statement = getConnection(URL).prepareStatement(query)){
			statement.setInt(1, alarm.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTimer(Timer timer) {
		String query = "update timers set hr = ?, min = ?, sec = ?, sound = ? where id = ?;";
		try (PreparedStatement statement = getConnection(URL).prepareStatement(query)){
			statement.setInt(1, timer.getHr());
			statement.setInt(2, timer.getMin());
			statement.setInt(3, timer.getSec());
			statement.setString(4, timer.getSound());
			statement.setInt(5, timer.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateAlarm(Alarm alarm) {
		String query = "update alarms set hr = ?, min = ?, sound = ? where id = ?;";
		try (PreparedStatement statement = getConnection(URL).prepareStatement(query)){
			statement.setInt(1, alarm.getHr());
			statement.setInt(2, alarm.getMin());
			statement.setString(3, alarm.getSound());
			statement.setInt(4, alarm.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Timer> getTimers(){
		String query = "select * from timers;";
		List<Timer> timers = new ArrayList<>();
		try(PreparedStatement statement = getConnection(URL).prepareStatement(query);ResultSet rs = statement.executeQuery()) {
			while(rs.next()) {
				int hr = rs.getInt("hr");
				int min = rs.getInt("min");
				int sec = rs.getInt("sec");
				int id = rs.getInt("id");
				String sound = rs.getString("sound");
				Timer timer = new Timer(hr,min,sec,sound);
				timer.setId(id);
				timers.add(timer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return timers;
	}
	
	public List<Alarm> getAlarms(){
		String query = "select * from alarms;";
		List<Alarm> alarms = new ArrayList<>();
		try(PreparedStatement statement = getConnection(URL).prepareStatement(query);ResultSet rs = statement.executeQuery()) {
			while(rs.next()) {
				int hr = rs.getInt("hr");
				int min = rs.getInt("min");
				int id = rs.getInt("id");
				String sound = rs.getString("sound");
				Alarm alarm = new Alarm(hr,min,sound);
				alarm.setId(id);
				alarms.add(alarm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alarms;
	} 
	
}
