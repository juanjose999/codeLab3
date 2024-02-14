package org.adaschool.api.service.user;

import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.user.User;
import org.adaschool.api.repository.user.repository.UserMongoRepository;
import org.adaschool.api.repository.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceMongoDb implements UsersService {
    @Autowired
    private UserRepository userMongoRepository;
    @Override
    public User save(User user) {
        return userMongoRepository.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<User> foundUser = userMongoRepository.findById(id);
        if(foundUser!=null){
            return userMongoRepository.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public List<User> all() {
        List<User> userList = userMongoRepository.all();
        return userList;
    }

    @Override
    public void deleteById(String id) {
        userMongoRepository.delete(id);
    }

    @Override
    public User update(User user, String userId) {
        return userMongoRepository.update(user, userId);

    }
}
