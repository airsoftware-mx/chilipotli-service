package com.airsoftware.chilipotliservice.service;

import com.airsoftware.chilipotliservice.exception.BadRequestException;
import com.airsoftware.chilipotliservice.model.Category;
import com.airsoftware.chilipotliservice.model.Item;
import com.airsoftware.chilipotliservice.model.Restaurant;
import com.airsoftware.chilipotliservice.repository.CategoryRepository;
import com.airsoftware.chilipotliservice.repository.ItemRepository;
import com.airsoftware.chilipotliservice.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

  private final ItemRepository itemRepository;

  public Iterable<Item> findAll() {
    return itemRepository.findAll();
  }

  public List<Item> findAll(Long restaurantId) {
    log.info("find all items for restaurant {}", restaurantId);
    return StreamSupport.stream(findAll().spliterator(), false)
        .filter(item -> item.getCategory().getRestaurant().getId().equals(restaurantId))
        .collect(Collectors.toList());
  }

  public Item create(Item item) {
    return itemRepository.save(item);
  }

  public Iterable<Item> create(List<Item> items) {
    return itemRepository.saveAll(items);
  }

  public void deleteAll() {
    itemRepository.deleteAll();
  }

  public Item update(Item item) {
    if (item.getId() == null) {
      throw new BadRequestException("Id to update item can't be null.");
    }
    return itemRepository.save(item);
  }
}
