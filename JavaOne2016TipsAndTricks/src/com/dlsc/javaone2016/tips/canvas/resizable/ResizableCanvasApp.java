package com.dlsc.javaone2016.tips.canvas.resizable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class ResizableCanvasApp extends Application {
  
  @Override
  public void start(Stage stage) throws Exception {
    ResizableCanvas canvas = new ResizableCanvas();
 
    canvas.setWidth(100);
    canvas.setHeight(100);
    
    StackPane stackPane = new StackPane();
    stackPane.getChildren().add(canvas);
 
    // Bind canvas size to stack pane size.
    canvas.widthProperty().bind(
                       stackPane.widthProperty());
    canvas.heightProperty().bind(
                       stackPane.heightProperty());
 
    stage.setScene(new Scene(stackPane));
    stage.setTitle("Resizable Canvas");
    stage.show();
  }
 
  public static void main(String[] args) {
    launch(args);
  }
}