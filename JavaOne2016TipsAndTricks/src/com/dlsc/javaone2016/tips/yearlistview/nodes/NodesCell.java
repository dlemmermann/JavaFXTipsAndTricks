package com.dlsc.javaone2016.tips.yearlistview.nodes;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class NodesCell extends ListCell<YearEntry> {

  private StackPane pane;
  private Label yearLabel;
  private Rectangle[] regions = new Rectangle[365];

  public NodesCell() {
    /*
     * Important, otherwise we will keep seeing a horizontal scrollbar.
     */
    setStyle("-fx-padding: 0px;");
 
    yearLabel = new Label();
    yearLabel
      .setStyle("-fx-padding: 10px; -fx-font-size: 1.2em; -fx-font-weight: bold;");
    StackPane.setAlignment(yearLabel, Pos.TOP_LEFT);

    pane = new StackPane();
    pane.getChildren().addAll(yearLabel);

    for (int i=0; i < 365; i++) {
      regions[i] = new Rectangle();
      regions[i].visibleProperty().bind(Bindings.not(emptyProperty()));
      regions[i].getStyleClass().add("value");
      regions[i].setFill(Color.GREEN);
      regions[i].setManaged(false);
    }

    pane.getChildren().addAll(regions);

    setGraphic(pane);
    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
  }
 
  @Override
  protected void updateItem(YearEntry entry, boolean empty) {
    super.updateItem(entry, empty);
    if (empty || entry == null) {
      yearLabel.setText("");
    } else {
      yearLabel.setText(Integer.toString(entry.getYear()));
    }
  }

  @Override
  protected void layoutChildren() {
    super.layoutChildren();

    YearEntry item = getItem();
    if (item != null) {
      double h = getHeight();
      double w = getWidth() / 365;
      double x = 0;
      for (int i = 0; i < 365; i++) {
        double value = item.getValues().get(i);
        double barHeight = h * value / 100;
        regions[i].setWidth(w);
        regions[i].setHeight(barHeight);
        regions[i].relocate(x, h - barHeight);
        x += w;
      }
    }
  }
}
