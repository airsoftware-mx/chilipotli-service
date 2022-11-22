package com.airsoftware.chilipotliservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailOrder {

  private String restaurant;
  private String orderDetail;
  private String delivery;
  private String address;
  private String subtotal;
  private String customerName;
  private String customerPhone;
  private String comments;

}
