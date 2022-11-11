package com.airsoftware.chilipotliservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Topping {

  private String name;
  private String type;
  private List<Item> items;
  private boolean selected;
  private Double cost;
  private String value;

}
