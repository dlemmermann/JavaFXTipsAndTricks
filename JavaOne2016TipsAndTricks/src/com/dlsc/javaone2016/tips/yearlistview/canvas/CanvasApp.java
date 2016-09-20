package com.dlsc.javaone2016.tips.yearlistview.canvas;

import com.dlsc.javaone2016.tips.util.Util;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class CanvasApp extends Application {
 
  @Override
  public void start(Stage stage) throws Exception {
 
    /*
     * Create some random data for my life span.
     */
    ObservableList<YearEntry> data = 
      FXCollections.observableArrayList();
    for (int year = 1969; year < 2015; year++) {
      YearEntry entry = new YearEntry(year);
      for (int day = 0; day < 365; day++) {
        entry.getValues().add(Math.random() * 100);
      }
      data.add(entry);
    }
 
    ListView<YearEntry> listView = new ListView<>(data);
    listView.setCellFactory(param -> new CanvasCell());
    listView.setFixedCellSize(200);
    listView.setOnScrollFinished(evt -> {
      Runtime runtime = Runtime.getRuntime();
      runtime.gc();
      long usedMemory = runtime.totalMemory() - runtime.freeMemory();
      stage.setTitle("Used Memory: " + Util.humanReadableByteCount(usedMemory, true));
    });
 
    Scene scene = new Scene(listView);
 
    stage.setTitle("Canvas Cell");
    stage.setScene(scene);
    stage.setWidth(600);
    stage.setHeight(600);
    stage.show();
  }
 
  public static void main(String[] args) {
    launch(args);
  }
}