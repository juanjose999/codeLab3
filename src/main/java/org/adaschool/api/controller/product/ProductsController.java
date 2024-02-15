package org.adaschool.api.controller.product;

import org.adaschool.api.exception.ProductNotFoundException;
import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.product.ProductDto;
import org.adaschool.api.service.product.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/v1/products")
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @GetMapping
    private ResponseEntity<List<Product>> all(){
        return new ResponseEntity<>(productsService.all(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable String id) {
        try {
            Optional<Product> product = productsService.findById(id);

            if (product.isPresent()) {
                return ResponseEntity.ok(product.get());
            } else {
                throw new ProductNotFoundException(id);
            }
        } catch (NoSuchElementException e) {
            throw new ProductNotFoundException(id);
        }
    }


    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productsService.save(product),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDto productDto) {
        Optional<Product> optionalUser = productsService.findById(id);

        if (optionalUser.isPresent()) {
            Product existinProduct = optionalUser.get();
            existinProduct.setName(productDto.getName());
            existinProduct.setDescription(productDto.getDescription());
            existinProduct.setCategory(productDto.getCategory());
            existinProduct.setPrice(productDto.getPrice());

            productsService.save(existinProduct);

            return ResponseEntity.ok().build();
        }
        else {
            throw  new ProductNotFoundException(id);
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        Optional<Product> existingProduct = productsService.findById(id);

        if (existingProduct.isEmpty()) {
            throw new ProductNotFoundException(id);
        }

        productsService.deleteById(id);
        return ResponseEntity.ok().build(); // Cambiado de noContent() a ok()
    }


}
