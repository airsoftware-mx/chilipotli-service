package com.airsoftware.chilipotliservice.control;

import com.airsoftware.chilipotliservice.model.Order;
import com.airsoftware.chilipotliservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
@CrossOrigin({"http://localhost:4200", "https://chilipotli.com", "https://cafeburrito.com.mx"})
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public Order create(@RequestBody Order order) {
    return orderService.submitOrder(order);
  }

  @GetMapping
  public Iterable<Order> findAll() {
    return orderService.findAllOrders();
  }

}
