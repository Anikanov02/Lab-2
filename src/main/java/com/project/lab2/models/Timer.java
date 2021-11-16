package com.project.lab2.models;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Timer implements Option{

	private int id = -1;
	private int hr;
	private int min;
	private int sec;
	private boolean active;
	private Runnable r;
	private Pane pane;

	public Timer(int hr,int min,int sec) {
		pane = new Pane();
		this.hr = hr;
		this.min = min;
		this.sec = sec;
		active = false;
		r = ()->{
			int h = this.hr;
			int m = this.min;
			int s = this.sec;
			while((h>0||m>0||s>0)&&active){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(s>0) {
					s--;
				}else {
					if(m>0) {
					m--;
					s = 59;
					}else {
						if(h>0) {
							h--;
							m = 59;
							s = 59;
						}else {
							break;
						}
					}
				}
				changeLabelText(h, m, s);
			}
			changeLabelText(this.hr, this.min, this.sec);
			while(active) {
				//SOUND
			}
		};
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

	@Override
	public void enable() {
		active = true;
		new Thread(r).start();
	}
	
	@Override
	public void disable() {
		active = false;
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
	
	public int getSec() {
		return sec;
	}
	
	public void setHr(int hr) {
		this.hr = hr;
	}
	
	public void setMin(int min) {
		this.min = min;
	}
	
	public void setSec(int sec) {
		this.sec = sec;
	}
	
	private void changeLabelText(int h,int m,int s) {
		Platform.runLater(()->{
			((Label)pane.getChildren().get(1)).setText(new StringBuilder().append(h).append(":").append(m).append(":").append(s).toString());
		});
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
