package com.jnasir.akka.services;

import com.jnasir.akka.Models.UserModels.Role;
import com.jnasir.akka.repository.RoleRepository;
import com.jnasir.akka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImp implements RoleService{

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List getRoles() {

        List<Role> role = new ArrayList<>();
        roleRepository.findAll()
                .forEach(role::add);
        return role;
    }
}
