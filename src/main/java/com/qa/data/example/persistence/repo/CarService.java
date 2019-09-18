package com.qa.data.example.persistence.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.data.example.persistence.domain.Car;

@Service
public class CarService {

	@Autowired
	private CarRepo repo;

	public Car createCar(Car car) {
		return this.repo.save(car);
	}

	public List<Car> findAll() {
		return this.repo.findAll();
	}

	public Car updateCar(Car car, Long id) {
		Car toUpdate = this.repo.findById(id).get();
		toUpdate.setModel(car.getModel());
		toUpdate.setPrice(car.getPrice());
		return this.repo.save(toUpdate);
	}

	public boolean deleteCar(Long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}

}
