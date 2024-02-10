package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.app.dao.*;
import com.app.pojos.*;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
	
	@Autowired
	private UserRepository userrepo;
	
	@PostMapping("/User")
	User newUser(@RequestBody User user) {
		return userrepo.save(user);
	}
	
	@GetMapping("/Users")
	List<User> getAllUsers(){
		return userrepo.findAll();
		
	}

}
