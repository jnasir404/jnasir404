package com.jnasir.akka.controllers;

import akka.actor.ActorRef;
import com.jnasir.akka.Models.UserModels.User;
import com.jnasir.akka.services.RoleService;
import com.jnasir.akka.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


//@Controller
@RestController
public class AuthenticationController {


	@Autowired()
	@Qualifier("UserActor")
	ActorRef userActor;

	@Autowired
	UserService userService;
	RoleService roleService;


	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register"); // resources/template/register.html
		return modelAndView;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin"); // resources/template/admin.html
		return modelAndView;
	}

	@RequestMapping(value="/register", method= RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		// Check for the validations
		if(bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "Please correct the errors in form!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		else if(userService.isUserAlreadyPresent(user)){
			modelAndView.addObject("successMessage", "user already exists!");			
		}
		// we will save the user if, no binding errors
		else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User is registered successfully!");
		}
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@RequestMapping(value="/getAllUsers", method= RequestMethod.GET)
	public ResponseEntity<Object> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@RequestMapping(value="/getAllRoles", method= RequestMethod.GET)
	public ResponseEntity<Object> getAllRoles() {
		return new ResponseEntity<>(userService.getAllRoles(), HttpStatus.OK);
	}

	@RequestMapping(value="/getCurrentUser", method= RequestMethod.GET)
	public ResponseEntity<Object> getCurrentUser() {
		return new ResponseEntity<>(userService.getCurrentUser(), HttpStatus.OK);
	}

	@RequestMapping(value="/getUserInfo", method= RequestMethod.GET)
	public ResponseEntity<Object> getUserInfo() {
		return new ResponseEntity<>(userService.getUserInfo(), HttpStatus.OK);
	}


}










