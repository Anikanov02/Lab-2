package com.project.lab2.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TimerController {

	@FXML
	private Button addButton;

	@FXML
	private Button alarmButton;

	@FXML
	private ListView<Pane> optionList;

	@FXML
	private Button editButton;

	@FXML
	private Label headLabel;

	@FXML
	private AnchorPane mainPane;

	@FXML
	private ScrollPane optionsPane;

    @FXML
	private Button timerButton;

	private static List<Pane> alarms  = new ArrayList<>();
	private static List<Pane> timers  = new ArrayList<>();
    
	private enum Option{
		TIMER,
		ALARM;
	}
	
	private Option o = Option.TIMER;
	
    @FXML
    public void initialize() {
    	alarmButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				o = Option.ALARM;
				optionList.getItems().clear();
				optionList.getItems().addAll(alarms);
			}
		});
    	
    	timerButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				o = Option.TIMER;
				optionList.getItems().clear();
				optionList.getItems().addAll(timers);
			}
		});
    	
    	editButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				switch(o) {
				case ALARM:
					alarms.stream().forEach((n)->{
						n.getChildren().get(2).setVisible(!n.getChildren().get(2).isVisible());
					});
					break;
				case TIMER:
					timers.stream().forEach((n)->{
						n.getChildren().get(2).setVisible(!n.getChildren().get(2).isVisible());
					});
					break;
				default:
					showErrorPage();
				}
			}
		});
    	
    	addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				openEditStage();
			}
		});
    	
    }
    
    public static void insertTimer(String hr, String min, String sec) {
    	Button invisibleEditButton = new Button("edit");
    	invisibleEditButton.setVisible(false);
    	timers.add(
    			new Pane(new Button(""),
    			new Label(new StringBuilder()
    			.append(hr).append(":")
    			.append(min).append(":")
    			.append(sec).toString()),
    			invisibleEditButton));
    }
    
    public static void insertAlarm(String hr, String min) {
    	Button invisibleEditButton = new Button("edit");
    	invisibleEditButton.setVisible(false);
    	alarms.add(
    			new Pane(new Button(""),
    			new Label(new StringBuilder()
    			.append(hr).append(":")
    			.append(min).append(":").toString()),
    			invisibleEditButton));
    }
    
    private void openEditStage() {
    	String path = "";
		switch(o) {
			case ALARM:
				path = "/alarmModifier.fxml";
				break;
			case TIMER:
				path = "/timerModifier.fxml";
				break;
			default: 
				showErrorPage();
				return;
		}
		openPage(path);
    }
    
    private void showErrorPage() {
    	openPage("/errorPage.fxml");
    }
    
    private void openPage(String url) {
		try {
			Stage stage = new Stage();
	    	Parent main = FXMLLoader.load(getClass().getResource(url));
			stage.setScene(new Scene(main));
			stage.show();	
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}