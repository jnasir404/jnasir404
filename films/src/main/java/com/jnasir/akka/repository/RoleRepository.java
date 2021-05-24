package com.jnasir.akka.repository;

import com.jnasir.akka.Models.UserModels.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

	public Role findByRole(String role);

}
