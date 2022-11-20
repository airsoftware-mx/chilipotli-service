package com.airsoftware.chilipotliservice.control;

import com.airsoftware.chilipotliservice.model.Category;
import com.airsoftware.chilipotliservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/category")
@CrossOrigin({"http://localhost:4200", "https://chilipotli.com", "https://cafeburrito.com.mx"})
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public Iterable<Category> getAll() {
    return categoryService.findAll();
  }

  @GetMapping("/{restaurantId}")
  public Iterable<Category> getAll(@PathVariable("restaurantId") Long restaurantId) {
    return categoryService.findAllByRestaurant(restaurantId);
  }

  @GetMapping("/{restaurantId}/menu")
  public Iterable<Category> getMenu(@PathVariable("restaurantId") Long restaurantId) {
    return categoryService.getMenu(restaurantId);
  }

  @PostMapping
  public Category create(@RequestBody Category category) {
    return categoryService.create(category);
  }

  @PostMapping("/bulk")
  public Iterable<Category> createBulk(@RequestBody List<Category> categories) {
    return categoryService.create(categories);
  }

  @PutMapping
  public Category update(@RequestBody Category category) {
    return categoryService.update(category);
  }

  @DeleteMapping
  public void deleteAll() {
    categoryService.deleteAll();
  }

}
