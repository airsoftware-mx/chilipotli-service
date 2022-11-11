package com.airsoftware.chilipotliservice.service;

import com.airsoftware.chilipotliservice.model.EmailOrder;
import com.airsoftware.chilipotliservice.model.Item;
import com.airsoftware.chilipotliservice.model.Order;
import com.airsoftware.chilipotliservice.model.Topping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired
  private MailSenderService mailSenderService;

  @Value("${delivery.cost}")
  private Double deliveryCost;

  public void sendOrderByEmail(Order order) {
    EmailOrder.EmailOrderBuilder emailOrder = EmailOrder.builder();

    StringBuilder orderDetail = new StringBuilder();
    for(Item item: order.getItems()) {
      orderDetail.append(item.getName()).append("<br>");
      if(item.getToppings() != null) {
        for(Topping topping : item.getToppings()) {
          if(topping.getType().equals("check")) {
            orderDetail.append("<span class=\"item\">").append(topping.getName()).append(": <b>").append(topping.isSelected() ? "Sí" : "No").append("</b></span><br>");
            if(topping.isSelected() && topping.getValue() != null) {
              orderDetail.append("<span class=\"topping\">").append(topping.getValue()).append("</span><br>");
            }
          } else {
            orderDetail.append("<span class=\"item\">").append(topping.getName()).append(": <b>").append(topping.getValue()).append("</b></span><br>");
          }
        }
      }
    }
    emailOrder.orderDetail(orderDetail.toString());
    emailOrder.customerName(order.getCustomer().getName());
    emailOrder.customerPhone(order.getCustomer().getPhone());
    emailOrder.delivery(order.getCustomer().getDeliveryOption().equals("delivery") ? "Envío" : "Recolección en tienda");
    emailOrder.address(order.getCustomer().getAddress());
    emailOrder.subtotal(String.format("$ %,.2f", order.getTotal()));
    mailSenderService.sendEmailNewOrder(emailOrder.build());
  }

}
