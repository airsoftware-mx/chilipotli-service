package com.airsoftware.chilipotliservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Item {

  private String name;
  private String description;
  private String image;
  private Double cost;
  private Double total;
  private List<Topping> toppings;

}
