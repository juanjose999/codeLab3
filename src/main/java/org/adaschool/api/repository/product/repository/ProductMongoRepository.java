package org.adaschool.api.repository.product.repository;

import org.adaschool.api.repository.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMongoRepository extends MongoRepository<Product, String> {
}
