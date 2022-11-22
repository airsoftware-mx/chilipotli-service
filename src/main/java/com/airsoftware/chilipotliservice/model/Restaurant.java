package com.airsoftware.chilipotliservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "restaurant-dev")
public class Restaurant {

  @Id
  private Long id;
  private String name;

}
