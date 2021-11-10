package com.project.lab2.models;

import javafx.scene.layout.Pane;

public class Timer implements Option{

	private int hr;
	private int min;
	private int sec;

	private Pane pane;
	
	public Timer(int hr,int min,int sec) {
		pane = new Pane();
		this.hr = hr;
		this.min = min;
		this.sec = sec;
	}
	
	public String getText() {
		return new StringBuilder().append(hr).append(":").append(min).append(":").append(sec).toString();
	}

	@Override
	public Pane getPane() {
		return pane;
	}

	@Override
	public void setPane(Pane pane) {
		this.pane = pane;
	}
	
}
