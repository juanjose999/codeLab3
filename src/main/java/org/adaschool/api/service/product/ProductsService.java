package org.adaschool.api.service.product;

import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.product.ProductDto;
import org.adaschool.api.repository.product.ProductResponseDto;

import java.util.List;
import java.util.Optional;

public interface ProductsService {

    List<Product> all();

    Optional<Product> findById(String id);

    Product save(Product product);

    Product update(Product product, String ProductId);

    void deleteById(String id);
}
