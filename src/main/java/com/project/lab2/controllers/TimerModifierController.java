package com.project.lab2.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;

public class TimerModifierController {

    @FXML
    private Button confirmButton;

    @FXML
    private Label hoursLabel;

    @FXML
    private Spinner<Integer> hoursSpinner;

    @FXML
    private Label minuresLabel;

    @FXML
    private Spinner<Integer> minutesSpinner;

    @FXML
    private Label secondsLabel;

    @FXML
    private Spinner<Integer> secondsSpinner;
    
    private static int hr;
    private static int min;
    private static int sec;
    
    @FXML
    public void initialize() {	
    	hr = -1;
    	min = -1;
    	sec = -1;
    	confirmButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				hr = hoursSpinner.getValue();
				min = minutesSpinner.getValue();
				sec = secondsSpinner.getValue();
				confirmButton.getScene().getWindow().hide();
			}	
		});
    	
    	hoursSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,0));
    	minutesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
    	secondsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0));
    }

    public static int getHr() {
    	return hr;
    }
    
    public static int getMin() {
    	return min;
    }
    
    public static int getSec() {
    	return sec;
    }
}
