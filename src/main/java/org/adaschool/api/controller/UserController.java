package org.adaschool.api.controller;

import org.adaschool.api.repository.user.User;
import org.adaschool.api.repository.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> all(){
        return new ResponseEntity(userRepository.all(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable String id){
        return new ResponseEntity(userRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user ){
        return new ResponseEntity<>(userRepository.save(user),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody User user){
        try{
            Optional<User> isUpdateUser = Optional.ofNullable(userRepository.update(user, id));
            if(isUpdateUser.isPresent()){
                return new ResponseEntity("the booking update is ok." , HttpStatus.OK);
            }else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        }catch (NoSuchElementException e){
            return new ResponseEntity("the user " + id + " does't in the data base.", HttpStatus.NOT_FOUND);
        }
    }
}
