package com.project.lab2.controllers;

import com.project.lab2.dao.SoundsPropertiesHandler;
import com.project.lab2.models.Timer;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    
    @FXML
    private ComboBox<String> soundComboBox;

    @FXML
    private Label soundLabel;
    
    private static Timer timer;
    
    private static Timer preSet;
    
    @FXML
    public void initialize() {	
    	
    	timer = Timer.nullTimer();
    	
    	if(preSet!=null) {
    		timer = preSet;
    		preSet = null;
    	}
    	
    	confirmButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				timer.setHr(hoursSpinner.getValue());
				timer.setMin(minutesSpinner.getValue());
				timer.setSec(secondsSpinner.getValue());
				timer.setSound(soundComboBox.getValue());
				confirmButton.getScene().getWindow().hide();
			}	
		});
    	
    	hoursSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(timer.getHr()==-1?0:timer.getHr(),23,0));
    	
    	minutesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(timer.getMin()==-1?0:timer.getMin(), 59, 0));
    	
    	secondsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(timer.getSec()==-1?0:timer.getSec(),59,0));
    	
    	soundComboBox.setItems(FXCollections.observableArrayList(SoundsPropertiesHandler.getSoundOptions()));
    	soundComboBox.setValue(timer.getSound());
    	
    }
    
    public static void setTimer(Timer preSet) {
		TimerModifierController.preSet = preSet;
    }
    
    public static Timer getTimer() {
    	return timer;
    }
     
}
