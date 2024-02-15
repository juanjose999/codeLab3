package org.adaschool.api.controller.product;

import org.adaschool.api.repository.product.Product;
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
    public ResponseEntity<Product> findById(@PathVariable String id){
        try{
            return new ResponseEntity(productsService.findById(id), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("the producto " + id + "does't in the data base." , HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productsService.save(product),HttpStatus.CREATED);
    }

    @PutMapping("/{idProduct}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product){
        try{
            Optional<Product> isUpdateProduct = Optional.ofNullable(productsService.update(product, id));
            if(isUpdateProduct.isPresent()){
                return new ResponseEntity("the product update is ok.", HttpStatus.OK);
            }else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        }catch (NoSuchElementException e){
            return new ResponseEntity("the product " + id + " does't in the data base.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        productsService.deleteById(id);
    }
}
