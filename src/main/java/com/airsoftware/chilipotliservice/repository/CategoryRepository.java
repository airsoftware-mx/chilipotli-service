package com.airsoftware.chilipotliservice.repository;

import com.airsoftware.chilipotliservice.model.Category;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends DatastoreRepository<Category, Long> {

  Iterable<Category> findByOrderBySortAsc();

}
