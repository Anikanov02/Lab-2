package com.project.lab2.models;

import java.time.LocalTime;
import java.util.Calendar;
import javafx.scene.layout.Pane;

public class Alarm implements Option{

	private int hr;
	private int min;
	private boolean active;
	private Pane pane;
	private Runnable r;
	private int id = -1;
	
	public Alarm(int hr,int min) {
		pane = new Pane();
		this.hr = hr;
		this.min = min;
		active = false;
		r = ()->{
			LocalTime lt = LocalTime.now(Calendar.getInstance().getTimeZone().toZoneId());
			while((!(lt.getHour()==hr&&lt.getMinute()==min))&&active) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(lt.getHour()+" "+lt.getMinute());
				lt = LocalTime.now(Calendar.getInstance().getTimeZone().toZoneId());
			}
			while(active) {
				//SOUND
			}
		};
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
		new Thread(r).start();
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

}
