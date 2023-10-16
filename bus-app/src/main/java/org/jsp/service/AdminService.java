package org.jsp.service;

import java.util.Optional;

import org.jsp.dao.AdminDao;
import org.jsp.dto.ResponseStructure;
import org.jsp.dto.Admin;
import org.jsp.exception.IdNotFoundException;
import org.jsp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	
	@Autowired
	AdminDao dao;

	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		structure.setBody(dao.saveAdmin(admin));
		structure.setMessage("Data Saved Succesfully");
		structure.setCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		structure.setBody(dao.updateAdmin(admin));
		structure.setMessage("Data updated Succesfully");
		structure.setCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<Admin>> findById(int id) {

		Optional<Admin> optref = (dao.findById(id));

		if (optref.isPresent()) {
			ResponseStructure<Admin> structure = new ResponseStructure<>();
			structure.setBody(optref.get());
			structure.setMessage("Data is available for above id");
			structure.setCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.ACCEPTED);
		}

		throw new IdNotFoundException();

	}

	public ResponseEntity<ResponseStructure<Admin>> verifyByEmail(String email, String password) {

		Optional<Admin> optref = (dao.verifyByEmail(email, password));

		if (optref.isPresent()) {
			ResponseStructure<Admin> structure = new ResponseStructure<>();
			structure.setBody(optref.get());
			structure.setMessage("Data is  found  ");
			structure.setCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.ACCEPTED);
		}

		throw new InvalidCredentialsException();

	}
}
