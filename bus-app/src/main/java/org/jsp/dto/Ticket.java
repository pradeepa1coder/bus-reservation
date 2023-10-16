package org.jsp.dto;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String ticket_no;
	@Column(nullable = false)
	private String seat_no;
	private double cost;
//	@Column(nullable = false)
	@CreationTimestamp
	private LocalDate time_of_booking;
	@Column(nullable = false)
	private int number_of_seats;

	@ManyToOne
	@JoinColumn(name = "buses_id")
	@JsonIgnore
	private Bus bus;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

}
