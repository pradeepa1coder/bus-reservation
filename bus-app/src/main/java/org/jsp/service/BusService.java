package org.jsp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jsp.dao.AdminDao;
import org.jsp.dao.BusDao;
import org.jsp.dto.Admin;
import org.jsp.dto.Bus;
import org.jsp.dto.ResponseStructure;
import org.jsp.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BusService {
	@Autowired
	BusDao busDao;

	@Autowired
	AdminDao adminDao;

	public ResponseEntity<ResponseStructure<Bus>> saveBus(Bus bus, int a_id) {
		Optional<Admin> respdata = adminDao.findById(a_id);
		if (respdata.isPresent()) {
			ResponseStructure<Bus> structure = new ResponseStructure<>();
			Admin a = respdata.get();
			a.getBuses().add(bus);
			adminDao.updateAdmin(a);
			bus.setAdmin(a);
			busDao.saveBus(bus);
			
			structure.setBody(bus);
			structure.setMessage("Bus succesfully saved!!!!");
			structure.setCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Bus>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();

	}

	public ResponseEntity<ResponseStructure<Bus>> updateBus(Bus bus) {
		ResponseStructure<Bus> structure = new ResponseStructure<>();
		busDao.updateBus(bus);
		structure.setBody(bus);
		structure.setMessage("Bus succesfully updated!!!!");
		structure.setCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Bus>>(structure, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<List<Bus>>> filter(String from, String to, LocalDate dop) {

		ResponseStructure<List<Bus>> structure = new ResponseStructure<>();
		structure.setBody(busDao.filter(from, to, dop));
		structure.setMessage("List of Buses : ");
		structure.setCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Bus>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Bus>> findById(int bid) {

		Optional<Bus> resdata = busDao.findById(bid);
		if (resdata.isPresent()) {
			ResponseStructure<Bus> structure = new ResponseStructure<>();
			structure.setBody(resdata.get());
			structure.setMessage("Bus has founded");
			structure.setCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Bus>>(structure, HttpStatus.FOUND);
		}
		throw new IdNotFoundException();
//		return null;
	}
}
