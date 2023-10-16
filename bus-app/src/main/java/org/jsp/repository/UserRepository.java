package org.jsp.repository;

import java.util.Optional;

import org.jsp.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("select u from User u where u.email=?1 and u.password=?2")
	public Optional<User> verifyByEmail(String email,String password);

}
