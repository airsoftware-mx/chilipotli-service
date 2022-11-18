package com.airsoftware.chilipotliservice.control;

import com.airsoftware.chilipotliservice.model.Restaurant;
import com.airsoftware.chilipotliservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/restaurant")
@CrossOrigin({"http://localhost:4200", "https://chilipotli.com"})
public class RestaurantController {

  private final RestaurantService restaurantService;

  @GetMapping
  public Iterable<Restaurant> getAll() {
    return restaurantService.findAll();
  }

  @PostMapping
  public Restaurant create(@RequestBody Restaurant restaurant) {
    return restaurantService.create(restaurant);
  }

  @PutMapping
  public Restaurant update(@RequestBody Restaurant restaurant) {
    return restaurantService.update(restaurant);
  }

  @DeleteMapping
  public void deleteAll() {
    restaurantService.deleteAll();
  }

}
