package org.adaschool.api.controller;

import org.adaschool.api.repository.product.Product;
import org.adaschool.api.service.product.ProductsService;
import org.adaschool.api.service.product.ProductsServiceMongoDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private ProductsService productsServiceMongoDb;

    @GetMapping
    public ResponseEntity<List<Product>> all(){
        return new ResponseEntity<>(productsServiceMongoDb.all(), HttpStatus.OK);
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<Product> findById(@PathVariable String id){
        try {
            return new ResponseEntity(productsServiceMongoDb.findById(id),HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("the producto " + id + "does't in the data base." , HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productsServiceMongoDb.save(product),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable String id, Product product){
        try {
            Optional<Product> isUpdateProduct = Optional.ofNullable(productsServiceMongoDb.update(product, id));
            if(isUpdateProduct.isPresent()){
                return new ResponseEntity("the product update is ok.", HttpStatus.OK);
            }else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        }catch (NoSuchElementException e){
            return new ResponseEntity("the product " + id + " does't in the data base.", HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping("/{idProduct}")
    public void deleteById(@PathVariable String id){
        productsServiceMongoDb.deleteById(id);
    }

}
