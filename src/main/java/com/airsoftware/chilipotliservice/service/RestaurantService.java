package com.airsoftware.chilipotliservice.service;

import com.airsoftware.chilipotliservice.exception.BadRequestException;
import com.airsoftware.chilipotliservice.model.Restaurant;
import com.airsoftware.chilipotliservice.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RestaurantService {

  private final RestaurantRepository restaurantRepository;

  public Iterable<Restaurant> findAll() {
    return restaurantRepository.findAll();
  }

  public Restaurant create(Restaurant item) {
    return restaurantRepository.save(item);
  }

  public void deleteAll() {
    restaurantRepository.deleteAll();
  }

  public Restaurant update(Restaurant item) {
    if (item.getId() == null) {
      throw new BadRequestException("Id to update restaurant can't be null.");
    }
    return restaurantRepository.save(item);
  }
}
