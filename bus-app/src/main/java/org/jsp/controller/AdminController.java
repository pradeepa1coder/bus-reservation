package org.jsp.controller;

import org.jsp.dto.ResponseStructure;
import org.jsp.dto.Admin;
import org.jsp.service.AdminService;
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
public class AdminController {
	@Autowired
	AdminService service;

	@PostMapping("/admindata")
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@RequestBody Admin admin) {

		return service.saveAdmin(admin);
	}

	@PutMapping("/admindata")
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@RequestBody Admin admin) {

		return service.updateAdmin(admin);
	}

	@GetMapping("/admindata/{id}")
	public ResponseEntity<ResponseStructure<Admin>> findById(@PathVariable int id) {

		return service.findById(id);
	}

	@GetMapping("/admindata")
	public ResponseEntity<ResponseStructure<Admin>> verifyByEmail(@RequestParam String email, @RequestParam String password) {

		return service.verifyByEmail(email, password);
	}
}
