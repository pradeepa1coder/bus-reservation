package org.jsp.controller;

import org.jsp.dto.ResponseStructure;
import org.jsp.dto.Ticket;
import org.jsp.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

	@Autowired
	TicketService ticketService;

	@PostMapping(value = "/ticketdata/{bid}/{uid}")
	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(@RequestBody Ticket ticket, @PathVariable int bid,
			@PathVariable int uid) {
		return ticketService.saveTicket(ticket, bid, uid);
	}

	@PutMapping(value = "/ticketdata/{bid}/{uid}")
	public ResponseEntity<ResponseStructure<Ticket>> updateTicket(@RequestBody Ticket ticket, @PathVariable int bid,
			@PathVariable int uid) {
		return ticketService.updateTicket(ticket, bid, uid);
	}

	@DeleteMapping(value = "/ticketdata/{id}")
	public ResponseEntity<ResponseStructure<Ticket>> cancellTicket(@PathVariable int id) {
		return ticketService.cancellTicket(id);
	}
}
