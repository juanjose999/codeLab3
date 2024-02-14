package org.adaschool.api.repository.user.repository;

import org.adaschool.api.repository.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    private UserMongoRepository userMongoRepository;

    @Override
    public List<User> all() {
        return userMongoRepository.findAll();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.of(userMongoRepository.findById(id).get());
    }

    @Override
    public User save(User user) {
        return userMongoRepository.save(user);
    }

    @Override
    public User update( User user, String id) {
        Optional<User> foundUser = findById(id);
        if(foundUser.isPresent()){
            User existinUser = foundUser.get();
            existinUser.setName(user.getName());
            existinUser.setLastName(user.getLastName());
            existinUser.setEmail(user.getEmail());
            return userMongoRepository.save(user);
        }
        return null;
    }

    @Override
    public void delete(String id) {
        Optional<User> userExist = findById(id);
        if(userExist.isPresent()){
            userMongoRepository.deleteById(id);
        }
    }
}
