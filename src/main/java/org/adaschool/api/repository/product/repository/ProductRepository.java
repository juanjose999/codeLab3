package org.adaschool.api.repository.product.repository;

import org.adaschool.api.repository.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> all();

    Optional<Product> findById(String id);

    Product save(Product product);

    Product update(Product product, String ProductId);

    void delete(String id);

}
