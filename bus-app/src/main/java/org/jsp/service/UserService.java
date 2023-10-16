package org.jsp.service;

import java.util.Optional;

import org.jsp.dao.UserDao;
import org.jsp.dto.ResponseStructure;
import org.jsp.dto.User;
import org.jsp.exception.IdNotFoundException;
import org.jsp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserDao dao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setBody(dao.saveUser(user));
		structure.setMessage("Data Saved Succesfully");
		structure.setCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setBody(dao.updateUser(user));
		structure.setMessage("Data updated Succesfully");
		structure.setCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<User>> findById(int id) {

		Optional<User> optref = (dao.findById(id));

		if (optref.isPresent()) {
			ResponseStructure<User> structure = new ResponseStructure<>();
			structure.setBody(optref.get());
			structure.setMessage("Data is available for above id");
			structure.setCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}

		throw new IdNotFoundException();

	}

	public ResponseEntity<ResponseStructure<User>> verifyByEmail(String email, String password) {

		Optional<User> optref = (dao.verifyByEmail(email, password));

		if (optref.isPresent()) {
			ResponseStructure<User> structure = new ResponseStructure<>();
			structure.setBody(optref.get());
			structure.setMessage("Data is  found  ");
			structure.setCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}

		throw new InvalidCredentialsException();

	}

}
