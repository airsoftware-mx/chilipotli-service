package com.airsoftware.chilipotliservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Order {

  private List<Item> items;
  private Customer customer;
  private Double total;

}
