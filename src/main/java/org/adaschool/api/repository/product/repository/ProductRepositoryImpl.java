package org.adaschool.api.repository.product.repository;

import org.adaschool.api.repository.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class ProductRepositoryImpl implements ProductRepository{

    @Autowired
    private ProductMongoRepository productMongoRepository;


    @Override
    public List<Product> all() {
        return productMongoRepository.findAll();
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.of(productMongoRepository.findById(id).get());
    }

    @Override
    public Product save(Product producto) {
        return productMongoRepository.save(producto);
    }

    public Product update( Product producto, String id) {
        Optional<Product> foundProduct = findById(id);
        if(foundProduct.isPresent()){
            Product existingProduct = foundProduct.get();
            existingProduct.setName(producto.getName());
            existingProduct.setDescription(producto.getDescription());
            existingProduct.setCategory(producto.getCategory());
            existingProduct.setTags(producto.getTags());
            existingProduct.setPrice(producto.getPrice());
            existingProduct.setImageUrl(producto.getImageUrl());
            return productMongoRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public void delete(String id) {
        Optional<Product> foundProduct = findById(id);
        if (foundProduct.isPresent()) {
            productMongoRepository.deleteById(id);
        }
    }
}
