package com.airsoftware.chilipotliservice.repository;

import com.airsoftware.chilipotliservice.model.Item;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends DatastoreRepository<Item, Long> {
}
