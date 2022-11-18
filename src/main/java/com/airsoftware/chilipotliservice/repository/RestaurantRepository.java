package com.airsoftware.chilipotliservice.repository;

import com.airsoftware.chilipotliservice.model.Restaurant;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends DatastoreRepository<Restaurant, Long> {
}
