package com.airsoftware.chilipotliservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Category {

  @Id
  private Long id;
  private Integer sort;
  private Restaurant restaurant;
  private String name;
  private String label;
  private String subtitle;
  @Transient
  private List<Item> items;
  private String color;
  private String titleColor;
  private String backgroundColor;
  private String labelColor;

}
