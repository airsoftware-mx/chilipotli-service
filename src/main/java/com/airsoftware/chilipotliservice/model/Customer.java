package com.airsoftware.chilipotliservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {

  private String address;
  private String name;
  private String phone;
  private String deliveryOption;

}
