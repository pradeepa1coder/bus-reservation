package org.jsp.repository;

import java.util.Optional;

import org.jsp.dto.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	@Query("select a from Admin a where a.email=?1 and a.password=?2")
	public Optional<Admin> verifyByEmail(String email,String password);


}
