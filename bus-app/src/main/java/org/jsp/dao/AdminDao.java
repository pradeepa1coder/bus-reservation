package org.jsp.dao;

import java.util.Optional;

import org.jsp.dto.Admin;
import org.jsp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {

	@Autowired
	AdminRepository repository;

	public Admin saveAdmin(Admin admin) {
		Admin u = repository.save(admin);
		return u;
	}

	public Admin updateAdmin(Admin admin) {
		return repository.save(admin);
	}

	public Optional<Admin> findById(int id) {
		return repository.findById(id);
	}

	public Optional<Admin> verifyByEmail(String email, String password) {
		return repository.verifyByEmail(email, password);
	}
}
