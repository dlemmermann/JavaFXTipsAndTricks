package com.dlsc.javaone2016.tips.yearlistview.canvas;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.util.Collections;
import java.util.List;

public class CanvasCell extends ListCell<YearEntry> {
 
  private Label yearLabel;
  private ResizableCellCanvas canvas;
 
  public CanvasCell() {
    /*
     * Important, otherwise we will keep seeing a horizontal scrollbar.
     */
    setStyle("-fx-padding: 0px;");
 
    yearLabel = new Label();
    yearLabel.setStyle("-fx-padding: 10px; -fx-font-size: 1.2em; -fx-font-weight: bold;");
    StackPane.setAlignment(yearLabel, Pos.TOP_LEFT);
 
    /*
     * Create a resizable canvas and bind its width and height to the width
     * and height of the table cell.
     */
    canvas = new ResizableCellCanvas();
    canvas.widthProperty().bind(widthProperty());
    canvas.heightProperty().bind(heightProperty());
 
    StackPane pane = new StackPane();
    pane.getChildren().addAll(yearLabel, canvas);
 
    setGraphic(pane);
    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
  }
 
  @Override
  protected void updateItem(YearEntry entry, boolean empty) {
    if (empty || entry == null) {
      yearLabel.setText("");
      canvas.setData(Collections.emptyList());
      canvas.draw();
    } else {
      yearLabel.setText(Integer.toString(entry.getYear()));
      canvas.setData(entry.getValues());
      canvas.draw();
    }
  }
 
  /*
   * Canvas is normally not resizable but by overriding isResizable() and
   * binding its width and height to the width and height of the cell it will
   * automatically resize.
   */
  class ResizableCellCanvas extends Canvas {
 
    private List<Double> data = Collections.emptyList();
 
    public ResizableCellCanvas() {
 
      /*
       * Make sure the canvas draws its content again when its size
       * changes.
       */
      widthProperty().addListener(it -> draw());
      heightProperty().addListener(it -> draw());
    }
 
    @Override
    public boolean isResizable() {
      return true;
    }
 
    @Override
    public double prefWidth(double height) {
      return getWidth();
    }
 
    @Override
    public double prefHeight(double width) {
      return getHeight();
    }
 
    public void setData(List<Double> data) {
      this.data = data;
    }
 
    /*
     * Draw a chart based on the data provided by the model.
     */
    private void draw() {
      GraphicsContext gc = getGraphicsContext2D();
      gc.clearRect(0, 0, getWidth(), getHeight());
 
      Stop[] stops = new Stop[] { new Stop(0, Color.SKYBLUE),
          new Stop(1, Color.SKYBLUE.darker().darker()) };
      LinearGradient gradient = new LinearGradient(0, 0, 0, 300, false,
          CycleMethod.NO_CYCLE, stops);
 
      gc.setFill(gradient);
 
      double availableHeight = getHeight() * .8;
      double counter = 0;
      for (Double value : data) {
        double x = getWidth() / 365 * counter;
        double barHeight = availableHeight * value / 100;
        double barWidth = getWidth() / 365 + 1;
        gc.fillRect(x, getHeight() - barHeight, barWidth, barHeight);
        counter++;
      }
    }
  }
}
