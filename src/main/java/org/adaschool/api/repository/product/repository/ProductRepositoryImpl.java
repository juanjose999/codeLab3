package org.adaschool.api.repository.product.repository;

import org.adaschool.api.repository.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
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
        try {
            if (productMongoRepository != null) {
                Optional<Product> product = productMongoRepository.findById(id);
                return product;
            } else {
                System.err.println("ProductRepository is null in ProductServiceMongoDb.findById");
                return Optional.empty();
            }
        } catch (NoSuchElementException e) {
            System.err.println("Product with ID " + id + " not found in ProductServiceMongoDb.findById");
            return Optional.empty();
        } catch (NullPointerException e) {
            System.err.println("ProductRepository is null in ProductServiceMongoDb.findById");
            return Optional.empty();
        }
    }

    @Override
    public Product save(Product product) {
        if (productMongoRepository != null) {
            return productMongoRepository.save(product);
        } else {
            throw new IllegalStateException("ProductMongoRepository is null in ProductServiceMongoDb.save");
        }
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
        Optional<Product> idProduct = findById(id);
        if (idProduct.isPresent()) {
            productMongoRepository.deleteById(id);
        }
    }
}
