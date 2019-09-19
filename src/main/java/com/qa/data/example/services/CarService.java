package com.qa.data.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qa.data.example.persistence.domain.Car;
import com.qa.data.example.persistence.repo.CarRepo;

@Service
public class CarService {

	@Autowired
	private CarRepo repo;

	public ResponseEntity<Car> createCar(Car car) {
		return ResponseEntity.ok(this.repo.save(car));
	}

	public ResponseEntity<List<Car>> findAll() {
		return ResponseEntity.ok(this.repo.findAll());
	}

	public ResponseEntity<Car> updateCar(Car car, Long id) {
		Car toUpdate = this.repo.findById(id).get();
		toUpdate.setModel(car.getModel());
		toUpdate.setPrice(car.getPrice());
		return ResponseEntity.ok(this.repo.save(toUpdate));
	}

	public ResponseEntity<Boolean> deleteCar(Long id) {
		this.repo.deleteById(id);
		return ResponseEntity.ok(this.repo.existsById(id));
	}

}
