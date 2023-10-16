package org.jsp.dto;

import java.time.LocalDate;//yyyy-mm-dd
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(nullable = false, unique = true)
	private long phone;
	private String password;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private LocalDate dop;
	@Column(nullable = false, unique = true)
	private long aadhar;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Ticket> tickets;

}
