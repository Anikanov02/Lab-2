package com.project.lab2.models;

import java.io.BufferedInputStream;
import java.time.LocalTime;
import java.util.Calendar;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.project.lab2.dao.SoundsPropertiesHandler;

import javafx.scene.layout.Pane;

public class Alarm implements Option{
       
	private int hr;
	private int min;
	private boolean active;
	private Pane pane;
	private int id;
	private String sound;
	
	public Alarm(int hr,int min, String sound) {
		this.sound = sound; 
		id = -1;
		pane = new Pane();
		this.hr = hr;
		this.min = min;
		active = false;
	}

	public String getText() {
		return new StringBuilder().append(hr).append(":").append(min).toString();
	}

	@Override
	public Pane getPane() {
		return pane;
	}

	@Override
	public void setPane(Pane pane) {
		this.pane = pane;
	}
	
	@Override
	public void enable() {
		this.active = true;
		new Thread(()->{
			LocalTime lt = LocalTime.now(Calendar.getInstance().getTimeZone().toZoneId());
			while((!(lt.getHour()==hr&&lt.getMinute()==min))&&active) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lt = LocalTime.now(Calendar.getInstance().getTimeZone().toZoneId());
			}
			playSound();
		}).start();
	}
	
	@Override
	public void disable() {
		this.active = false;
	}
	
	@Override
	public boolean getActive() {
		return active;
	}
	
	public int getHr() {
		return hr;
	}
	
	public int getMin() {
		return min;
	}
	
	public void setHr(int hr) {
		this.hr = hr;
	}
	
	public void setMin(int min) {
		this.min = min;
	}
	
	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void playSound() {
		new Thread(()->{
			try {
			     Clip clip = AudioSystem.getClip();
			     AudioInputStream inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream(SoundsPropertiesHandler.getSoundUrl(sound))));
			     clip.open(inputStream);
			     clip.loop(Clip.LOOP_CONTINUOUSLY);
			     clip.start();
			     while(active) {
			    	 Thread.sleep(clip.getMicrosecondLength()/1000);
			     }
			     clip.stop();
			} catch (Exception e) {
			     System.err.println(e.getMessage());
			}
		}).start();
	}

	@Override
	public String getSound() {
		return sound;
	}

	@Override
	public void setSound(String url) {
		sound = url;
	}
	
	public static Alarm nullAlarm() {
    	return new Alarm(-1,-1,SoundsPropertiesHandler.getSoundOptions().get(0));
    }

}
