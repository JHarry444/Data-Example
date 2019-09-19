package com.qa.data.example.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.data.example.persistence.domain.Car;
import com.qa.data.example.services.CarService;

@RestController
@RequestMapping("/car")
public class CarController {
	@PutMapping("/update")
	public ResponseEntity<Car> update(@RequestBody Car car, @PathParam("id") Long id) {
		return this.service.updateCar(car, id);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
		return this.service.deleteCar(id);
	}

	private CarService service;

	public CarController(CarService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Car> createCar(@RequestBody Car car) {
		return this.service.createCar(car);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Car>> getAll() {
		return this.service.findAll();
	}

}
