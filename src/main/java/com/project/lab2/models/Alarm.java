package com.project.lab2.models;

import javafx.scene.layout.Pane;

public class Alarm implements Option{

	private int hr;
	private int min;

	private Pane pane;
	
	public Alarm(int hr,int min) {
		pane = new Pane();
		this.hr = hr;
		this.min = min;
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
	
}
