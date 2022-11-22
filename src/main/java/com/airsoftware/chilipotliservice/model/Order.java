package com.airsoftware.chilipotliservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "order-dev")
public class Order {

  @Id
  private Long id;
  private Restaurant restaurant;
  private List<Item> items;
  private Customer customer;
  private Double total;
  private String comments;

}
