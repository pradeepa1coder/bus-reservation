package org.jsp.dao;

import java.util.Optional;

import org.jsp.dto.Admin;
import org.jsp.dto.Ticket;
import org.jsp.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDao {

	@Autowired
	TicketRepository repository;

	public Ticket saveTicket(Ticket ticket) {
		return repository.save(ticket);
	}

	public Ticket updateTicket(Ticket ticket) {
		return repository.save(ticket);
	}
	public void deleteTicket(Ticket ticket) {
		 repository.delete(ticket);
	}

	public Optional<Ticket> findById(int id) {
		return repository.findById(id);
	}
}
