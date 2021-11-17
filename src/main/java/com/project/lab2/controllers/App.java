package com.project.lab2.controllers;

import com.project.lab2.dao.WindowPropertiesHandler;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class App extends Application {

	public static void main(String[] args) {
		Application.launch(args);	
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	
		Parent main = FXMLLoader.load(getClass().getResource(WindowPropertiesHandler.mainWindow()));
		primaryStage.setScene(new Scene(main));
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Platform.exit();
			}
		});
	}

}