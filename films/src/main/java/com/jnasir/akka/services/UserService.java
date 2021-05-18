package com.jnasir.akka.services;


import com.jnasir.akka.Models.User;

import java.util.List;

public interface UserService {

	public void saveUser(User user);
	
	public boolean isUserAlreadyPresent(User user);

	public List getAllUsers() ;

	public List getAllRoles();

	public Object[] getCurrentUser();

	public Object getUserInfo();
}
