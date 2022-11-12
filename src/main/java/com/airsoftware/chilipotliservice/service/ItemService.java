package com.airsoftware.chilipotliservice.service;

import com.airsoftware.chilipotliservice.model.Item;
import com.airsoftware.chilipotliservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  public Iterable<Item> findAll() {
    return itemRepository.findAll();
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

}
