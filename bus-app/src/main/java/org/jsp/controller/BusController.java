package org.jsp.controller;

import java.time.LocalDate;
import java.util.List;

import org.jsp.dto.Admin;
import org.jsp.dto.Bus;
import org.jsp.dto.ResponseStructure;
import org.jsp.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusController {

	@Autowired
	BusService service;

	@PostMapping("/busdata/{ad_id}")
	public ResponseEntity<ResponseStructure<Bus>> saveBus(@RequestBody Bus bus, @PathVariable int ad_id) {
		return service.saveBus(bus, ad_id);
	}
	
	@PutMapping("/busdata/update")
	public ResponseEntity<ResponseStructure<Bus>> updateBus(@RequestBody Bus bus) {
		return service.updateBus(bus);
	}

	@GetMapping("/busdata/filter")
	public ResponseEntity<ResponseStructure<List<Bus>>> filter(@RequestParam String from, @RequestParam String to,
			@RequestParam LocalDate dop) {

		return service.filter(from, to, dop);
	}

	@GetMapping("/busdata/findby/{bid}")
	public ResponseEntity<ResponseStructure<Bus>> findById(@PathVariable int bid) {
		return service.findById(bid);
	}

}
