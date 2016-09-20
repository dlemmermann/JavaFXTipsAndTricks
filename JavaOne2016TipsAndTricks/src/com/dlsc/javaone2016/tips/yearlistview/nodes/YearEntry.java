package com.dlsc.javaone2016.tips.yearlistview.nodes;

import java.util.ArrayList;
import java.util.List;


/**
 * Just some fake model object.
 */
public class YearEntry {
 
  private int year;
 
  public YearEntry(int year) {
    this.year = year;
  }
 
  public int getYear() {
    return year;
  }
 
  private List<Double> values = new ArrayList<>();
 
  /**
   * Stores the values shown in the chart.
   */
  public List<Double> getValues() {
    return values;
  }
}