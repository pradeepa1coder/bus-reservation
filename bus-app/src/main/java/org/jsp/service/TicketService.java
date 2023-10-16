package org.jsp.service;

import java.util.Optional;

import org.jsp.dao.BusDao;
import org.jsp.dao.TicketDao;
import org.jsp.dao.UserDao;
import org.jsp.dto.Bus;
import org.jsp.dto.EmailConfiguration;
import org.jsp.dto.ResponseStructure;
import org.jsp.dto.Ticket;
import org.jsp.dto.User;
import org.jsp.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

	@Autowired
	TicketDao ticketDao;
	@Autowired
	UserDao userDao;

	@Autowired
	BusDao busDao;

	@Autowired
	ResevationApiEmailService service;

	@Autowired
	private EmailConfiguration configuration;

	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(Ticket ticket, int bid, int uid) {

		Optional<Bus> busdata = busDao.findById(bid);
		Optional<User> userdata = userDao.findById(uid);
		ResponseStructure<Ticket> structure = new ResponseStructure<>();
		if (userdata.isPresent() && busdata.isPresent()) {
			Bus b = busdata.get();
			User u = userdata.get();
			ticket.setCost(ticket.getNumber_of_seats() * b.getCost_per_seat());

			b.getTickets().add(ticket);
			u.getTickets().add(ticket);
			ticket.setBus(b);
			ticket.setUser(u);

			configuration.setTo(u.getEmail());
			configuration.setSubject("confirmation on ticket booking");
			configuration.setText("Number of tickets booked : " + ticket.getNumber_of_seats());
			String message = service.sendEmail(configuration);

			structure.setBody(ticketDao.saveTicket(ticket));
			busDao.updateBus(busdata.get());
			userDao.updateUser(userdata.get());

			structure.setMessage(message);
			structure.setCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.CREATED);

		}

		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Ticket>> updateTicket(Ticket ticket, int bid, int uid) {

		Optional<Bus> busdata = busDao.findById(bid);
		Optional<User> userdata = userDao.findById(uid);
		ResponseStructure<Ticket> structure = new ResponseStructure<>();
		if (userdata.isPresent() && busdata.isPresent()) {
			Bus b = busdata.get();
			User u = userdata.get();
			ticket.setCost(ticket.getNumber_of_seats() * b.getCost_per_seat());

//			b.getTickets().add(ticket); no need***
//			u.getTickets().add(ticket); no need***
			ticket.setBus(b);
			ticket.setUser(u);

			configuration.setTo(u.getEmail());
			configuration.setSubject("confirmation on ticket booking");
			configuration.setText("Number of seats booked : " + ticket.getNumber_of_seats());
			String message = service.sendEmail(configuration);

			structure.setBody(ticketDao.updateTicket(ticket));
			busDao.updateBus(busdata.get());
			userDao.updateUser(userdata.get());

			structure.setMessage(message);
			structure.setCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.CREATED);

		}

		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Ticket>> cancellTicket(int id) {

		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Ticket> ticketdata = ticketDao.findById(id);
		if (ticketdata.isEmpty()) {
			throw new IdNotFoundException();
		} else {
			ticketDao.deleteTicket(ticketdata.get());
			structure.setBody("Succesfully deleted");
			structure.setMessage("Ticket found");
			structure.setCode(HttpStatus.OK.value());
			return new ResponseEntity(structure, HttpStatus.OK);
		}
	}
}
