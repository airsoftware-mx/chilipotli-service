package com.airsoftware.chilipotliservice.control;

import com.airsoftware.chilipotliservice.model.Item;
import com.airsoftware.chilipotliservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/item")
@CrossOrigin({"http://localhost:4200", "https://chilipotli.com"})
public class ItemController {

  private final ItemService itemService;

  @GetMapping
  public Iterable<Item> getAll() {
    return itemService.findAll();
  }

  @PostMapping
  public Item create(@RequestBody Item item) {
    return itemService.create(item);
  }

  @PostMapping("/bulk")
  public Iterable<Item> create(@RequestBody List<Item> items) {
    return itemService.create(items);
  }

  @DeleteMapping
  public void deleteAll() {
    itemService.deleteAll();
  }

}
