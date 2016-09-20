package com.dlsc.javaone2016.tips.scrolling;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SyncScrollingApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		DualListView view = new DualListView();
		Scene scene = new Scene(view);
		primaryStage.setScene(scene);
		primaryStage.setWidth(500);
		primaryStage.setHeight(400);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
