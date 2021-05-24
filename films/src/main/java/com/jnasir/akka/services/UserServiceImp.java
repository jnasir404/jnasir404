package com.jnasir.akka.services;

import com.jnasir.akka.Models.UserModels.Role;
import com.jnasir.akka.Models.UserModels.User;
import com.jnasir.akka.repository.RoleRepository;
import com.jnasir.akka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
    BCryptPasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	HttpServletRequest request;
	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		// Try to implement this method, as assignment.
		return false;
	}

	@Override
	public List getAllUsers() {
		List<User> user = new ArrayList<>();
		userRepository.findAll()
				.forEach(user::add);
		return user;

	}

	@Override
	public List getAllRoles() {
		List<Role> role = new ArrayList<>();
		roleRepository.findAll()
				.forEach(role::add);
		return role;

	}

	@Override
	public Object[] getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		userDetails.getUsername().toString();
		return userDetails.getAuthorities().toArray();
	}


	@Override
	public Object getUserInfo() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getDetails();
	}


}
