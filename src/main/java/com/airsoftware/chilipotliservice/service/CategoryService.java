package com.airsoftware.chilipotliservice.service;

import com.airsoftware.chilipotliservice.exception.BadRequestException;
import com.airsoftware.chilipotliservice.model.Category;
import com.airsoftware.chilipotliservice.model.Item;
import com.airsoftware.chilipotliservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final ItemService itemService;

  public Iterable<Category> findAll() {
    return categoryRepository.findAll();
  }

  public List<Category> findAllByRestaurant(Long restaurantId) {
    log.info("find all categories for restaurant {}", restaurantId);
    return StreamSupport.stream(findAll().spliterator(), false)
        .filter(category -> category.getRestaurant().getId().equals(restaurantId))
        .collect(Collectors.toList());
  }

  public Iterable<Category> getMenu(Long restaurantId) {
    final List<Item> items = itemService.findAll(restaurantId);
    return findAllByRestaurant(restaurantId).stream()
        .peek(category -> category.setItems(items.stream()
            .filter(item -> item.getCategory().getId().equals(category.getId()))
            .collect(Collectors.toList())))
        .collect(Collectors.toList());
  }

  public Category create(Category category) {
    return categoryRepository.save(category);
  }

  public Iterable<Category> create(List<Category> categories) {
    return categoryRepository.saveAll(categories);
  }

  public void deleteAll() {
    categoryRepository.deleteAll();
  }

  public Category update(Category category) {
    if (category.getId() == null) {
      throw new BadRequestException("Id to update category can't be null.");
    }
    return categoryRepository.save(category);
  }
}
