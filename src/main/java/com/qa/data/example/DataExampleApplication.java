package com.qa.data.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.qa.data.example.persistence.domain.Car;
import com.qa.data.example.persistence.repo.CarService;

@SpringBootApplication
public class DataExampleApplication {

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(DataExampleApplication.class, args);
		CarService service = ac.getBean(CarService.class);
		service.createCar(new Car("Honda", 44.94));
		System.out.println(service.findAll());
		service.updateCar(new Car("Prius", 9999.99), 1L);
		System.out.println(service.findAll());
		service.deleteCar(1L);
		System.out.println(service.findAll());
	}

}
