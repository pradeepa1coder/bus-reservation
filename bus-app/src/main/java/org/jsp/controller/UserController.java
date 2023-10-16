package org.jsp.controller;

import org.jsp.dto.ResponseStructure;
import org.jsp.dto.User;
import org.jsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	UserService service;

	@PostMapping("/userdata")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {

		return service.saveUser(user);
	}

	@PutMapping("/userdata")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {

		return service.updateUser(user);
	}

	@GetMapping("/userdata/{id}")
	public ResponseEntity<ResponseStructure<User>> findById(@PathVariable int id) {

		return service.findById(id);
	}

	@GetMapping("/userdata")
	public ResponseEntity<ResponseStructure<User>> verifyByEmail(@RequestParam String email, @RequestParam String password) {

		return service.verifyByEmail(email, password);
	}
}
