package com.airsoftware.chilipotliservice.model;

import lombok.*;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Item {

  @Id
  private Long id;
  private String category;
  private String name;
  private String description;
  private String image;
  private Double cost;
  private Double total;
  private List<Topping> toppings;

}
