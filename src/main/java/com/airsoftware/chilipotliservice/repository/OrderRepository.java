package com.airsoftware.chilipotliservice.repository;

import com.airsoftware.chilipotliservice.model.Order;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends DatastoreRepository<Order, Long> {
}
