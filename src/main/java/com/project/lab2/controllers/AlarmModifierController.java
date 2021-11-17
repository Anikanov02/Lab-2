package com.project.lab2.controllers;

import com.project.lab2.dao.SoundsPropertiesHandler;
import com.project.lab2.models.Alarm;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;

public class AlarmModifierController{

    @FXML
    private Button confirmButton;

    @FXML
    private Label hoursLabel;

    @FXML
    private  Spinner<Integer> hoursSpinner;

    @FXML
    private Label minutesLabel;

    @FXML
    private Spinner<Integer> minutesSpinner;
    
    @FXML
    private ComboBox<String> soundComboBox;

    @FXML
    private Label soundLabel;
    
    private static Alarm alarm;
    
    private static Alarm preSet;
    
    @FXML
    public void initialize() {

    	alarm = Alarm.nullAlarm();
    	
    	if(preSet!=null) {
    		alarm = preSet;
    		preSet = null;
    	}
    	
    	confirmButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				alarm.setHr(hoursSpinner.getValue());
				alarm.setMin(minutesSpinner.getValue());
				alarm.setSound(soundComboBox.getValue());
				confirmButton.getScene().getWindow().hide();
			}	
		});
    	
    	hoursSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(alarm.getHr()==-1?0:alarm.getHr(),23,0));
    	
    	minutesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(alarm.getMin()==-1?0:alarm.getMin(), 59, 0));
    	
    	soundComboBox.setItems(FXCollections.observableArrayList(SoundsPropertiesHandler.getSoundOptions()));
    	soundComboBox.setValue(alarm.getSound());
    	
    }
    
    public static void setAlarm(Alarm preSet) {
    	AlarmModifierController.preSet = preSet;
    }
    
    public static Alarm getAlarm() {
    	return alarm;
    }

}
