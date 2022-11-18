package com.airsoftware.chilipotliservice.repository;

import com.airsoftware.chilipotliservice.model.Category;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.cloud.gcp.data.datastore.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends DatastoreRepository<Category, Long> {

  @Override
  @Query("select * from category order by sort asc")
  Iterable<Category> findAll();

}
