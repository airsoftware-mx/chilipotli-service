package com.airsoftware.chilipotliservice.control;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class OrderController {

  @PostMapping
  public void generateOrder() {

  }

}
