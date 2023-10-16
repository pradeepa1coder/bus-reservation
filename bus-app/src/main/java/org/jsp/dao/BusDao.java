package org.jsp.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jsp.dto.Bus;
import org.jsp.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BusDao {

	@Autowired
	BusRepository repository;

	public Bus saveBus(Bus bus) {
		return repository.save(bus);
	}

	public List<Bus> filter(String from, String to, LocalDate dop) {
		return repository.filter(from, to, dop);
	}

	public Optional<Bus> findById(int id) {
		return repository.findById(id);
	}

	public Bus updateBus(Bus bus) {
		return repository.save(bus);

	}

}
