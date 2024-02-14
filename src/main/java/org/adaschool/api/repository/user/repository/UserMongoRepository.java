package org.adaschool.api.repository.user.repository;

import org.adaschool.api.repository.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMongoRepository extends MongoRepository<User, String> {
}
