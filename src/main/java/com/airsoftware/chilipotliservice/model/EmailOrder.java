package com.airsoftware.chilipotliservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailOrder {

  private String orderDetail;
  private String delivery;
  private String address;
//  private String pickUp;
  private String subtotal;
//  private String deliveryCost;
//  private String total;
//  private String payment;
  private String customerName;
  private String customerPhone;

}
