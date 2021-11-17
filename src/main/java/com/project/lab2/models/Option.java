package com.project.lab2.models;

import javafx.scene.layout.Pane;

public interface Option {

	public String getText();
	public Pane getPane();
	public void setPane(Pane pane);
	public void enable();
	public void disable();
	public boolean getActive();
	public void setId(int id);
	public int getId();
	public void playSound();
	public String getSound();
	public void setSound(String url);
	
}
