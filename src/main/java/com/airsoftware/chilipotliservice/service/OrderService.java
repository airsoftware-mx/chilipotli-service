package com.airsoftware.chilipotliservice.service;

import com.airsoftware.chilipotliservice.exception.BadRequestException;
import com.airsoftware.chilipotliservice.model.*;
import com.airsoftware.chilipotliservice.repository.OrderRepository;
import com.airsoftware.chilipotliservice.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final MailSenderService mailSenderService;
  private final OrderRepository orderRepository;
  private final RestaurantRepository restaurantRepository;

  @Value("${delivery.cost}")
  private Double deliveryCost;

  private void sendOrderByEmail(Order order) {
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
    emailOrder.restaurant(order.getRestaurant().getName());
    emailOrder.orderDetail(orderDetail.toString());
    emailOrder.customerName(order.getCustomer().getName());
    emailOrder.customerPhone(order.getCustomer().getPhone());
    emailOrder.delivery(order.getCustomer().getDeliveryOption().equals("delivery") ? "Envío" : "Recolección en tienda");
    emailOrder.address(order.getCustomer().getAddress());
    emailOrder.subtotal(String.format("$ %,.2f", order.getTotal()));
    if (order.getComments() != null) {
      emailOrder.comments(order.getComments());
    } else {
      emailOrder.comments("");
    }
    mailSenderService.sendEmailNewOrder(emailOrder.build());
  }

  public Order saveOrder(Order order) {
    return orderRepository.save(order);
  }

  public Order submitOrder(Order order) {
    if (order.getRestaurant() == null || order.getRestaurant().getId() == null) {
      throw new BadRequestException("Id of restaurant can't be null.");
    }
    final Optional<Restaurant> restaurantOptional = restaurantRepository.findById(order.getRestaurant().getId());
    if(restaurantOptional.isPresent()) {
      order.setRestaurant(restaurantOptional.get());
      sendOrderByEmail(order);
      return saveOrder(order);
    } else {
      throw new BadRequestException("Restaurant not found.");
    }
  }

  public Iterable<Order> findAllOrders() {
    return orderRepository.findAll();
  }

}
