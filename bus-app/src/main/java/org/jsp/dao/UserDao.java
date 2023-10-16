package org.jsp.dao;

import java.util.Optional;

import org.jsp.dto.User;
import org.jsp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	UserRepository repository;

	public User saveUser(User user) {
		User u = repository.save(user);
		return u;
	}

	public User updateUser(User user) {
		return repository.save(user);
	}

	public Optional<User> findById(int id) {
		return repository.findById(id);
	}

	public Optional<User> verifyByEmail(String email, String password) {
		return repository.verifyByEmail(email, password);
	}
}
