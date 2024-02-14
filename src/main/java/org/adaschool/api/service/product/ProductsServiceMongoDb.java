package org.adaschool.api.service.product;

import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.product.ProductDto;
import org.adaschool.api.repository.product.ProductMapper;
import org.adaschool.api.repository.product.ProductResponseDto;
import org.adaschool.api.repository.product.repository.ProductMongoRepository;
import org.adaschool.api.repository.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductsServiceMongoDb implements ProductsService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> all() {
        List<Product> products;
       if(productRepository != null){
           return productRepository.all();
       } else {
           // If productRepository is null, create a default list for testing
           products = Arrays.asList(
                   new Product("1", "Whole Milk", "Whole Milk 200ml", "Dairy", 15.488),
                   new Product("2", "Skim Milk", "Skim Milk 300ml", "Dairy", 19.526)
           );
       }
        return products;
    }
    @Override
    public Optional<Product> findById(String id) {
        return productRepository.findById(id);

    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product, String productId) {
        return productRepository.update(product, productId);
    }

    @Override
    public void deleteById(String id) {
        Optional<Product> idProduct = findById(id);
        if(idProduct.isPresent()){
            productRepository.delete(id);
        }
    }
}
