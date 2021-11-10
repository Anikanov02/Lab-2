package com.project.lab2.controllers;

import java.io.IOException;
import java.util.List;
import com.project.lab2.dao.DataAccessObject;
import com.project.lab2.models.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
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

	private static List<Alarm> alarms;
	private static List<Timer> timers;
	private DataAccessObject dao;
	
	public TimerController() {
		dao = DataAccessObject.getInstance();
		alarms = dao.getAlarms();
		timers = dao.getTimers();
	}
    
	private enum Mode{
		TIMER,
		ALARM;
	}
	
	private Mode o = Mode.TIMER;
	
    @FXML
    public void initialize() {
    	alarmButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				o = Mode.ALARM;
				optionList.getItems().clear();
				alarms.stream().forEach((n)->{
					optionList.getItems().add(n.getPane());
				});
			}
		});
    	
    	timerButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				o = Mode.TIMER;
				optionList.getItems().clear();
				timers.stream().forEach((n)->{
					optionList.getItems().add(n.getPane());
				});
			}
		});
    	
    	editButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				switch(o) {
				case ALARM:
					alarms.stream().forEach((n)->{
						n.getPane().getChildren().get(2).setVisible(!n.getPane().getChildren().get(2).isVisible());
						addButton.setDisable(n.getPane().getChildren().get(2).isVisible());
					});
					break;
				case TIMER:
					timers.stream().forEach((n)->{
						n.getPane().getChildren().get(2).setVisible(!n.getPane().getChildren().get(2).isVisible());
						addButton.setDisable(n.getPane().getChildren().get(2).isVisible());
					});
					break;
				default:
					showErrorPage();
					return;
				}
			}
		});
    	
    	addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				editButton.setDisable(true);
				openEditStage();
				editButton.setDisable(false);
				switch(o) {
				case ALARM:
					if(AlarmModifierController.getHr()!=-1) {
						insertAlarm(AlarmModifierController.getHr(), AlarmModifierController.getMin());
					}	
					break;
				case TIMER:
					if(TimerModifierController.getHr()!=-1) {
						insertTimer(TimerModifierController.getHr(), TimerModifierController.getMin(), TimerModifierController.getSec());
					}		
					break;
				default:
					showErrorPage();
					return;
				}
			}
		});
    	
    }
    
    public void insertTimer(int hr, int min, int sec) {
    	Timer timer = new Timer(hr,min,sec);
    	timer.setPane(formPane(timer));
    	timers.add(timer);
    	optionList.getItems().add(timer.getPane());
    	dao.insertTimer(timer);
    }
    
    public void insertAlarm(int hr, int min) {
    	Alarm alarm = new Alarm(hr,min);
    	alarm.setPane(formPane(alarm));
    	alarms.add(alarm);
    	optionList.getItems().add(alarm.getPane());
    	dao.insertAlarm(alarm);
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
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(mainPane.getScene().getWindow());
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private Pane formPane(Option o) {
    	Button invisibleEditButton = new Button("edit");
    	invisibleEditButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				openEditStage();
				
			}
    	});
    	invisibleEditButton.setVisible(false);
    	ToggleButton tb = new ToggleButton("");
    	Pane newOption = new Pane();
  
    	newOption.getChildren().add(tb);
    	newOption.getChildren().add(new Label(o.getText()));
    	newOption.getChildren().add(invisibleEditButton);
    	
    	newOption.getChildren().get(1).setLayoutX(20);
    	newOption.getChildren().get(2).setLayoutX(256);    	
    	return newOption;
    }

}