package org.adaschool.api.repository.user.repository;

import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> all();

    Optional<User> findById(String id);

    User save(User user);

    User update(User user, String userId);

    void delete(String id);
}
