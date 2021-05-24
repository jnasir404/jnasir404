package com.jnasir.akka.repository;

import com.jnasir.akka.Models.UserModels.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
