package com.qa.data.example.services;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.data.example.persistence.domain.Car;
import com.qa.data.example.persistence.repo.CarRepo;

@RunWith(SpringRunner.class)
public class CarServiceUnitTest {

	private final Car CAR = new Car("taurus", 44.94);
	private final Long ID = 1L;

	@Before
	public void init() {
		this.CAR.setId(ID);
	}

	@Test
	public void createCar() {
		Mockito.when(this.repo.save(CAR)).thenReturn(CAR);

		ResponseEntity<Car> testResponse = ResponseEntity.ok(CAR);

		assertEquals(testResponse, this.service.createCar(CAR));

		Mockito.verify(this.repo).save(CAR);
	}

	@Test
	public void testUpdateCar() {
		Mockito.when(this.repo.findById(ID)).thenReturn(Optional.of(CAR));
		Mockito.when(this.repo.save(this.CAR)).thenReturn(CAR);
		ResponseEntity<Car> testResponse = ResponseEntity.ok(CAR);

		assertEquals(testResponse, this.service.updateCar(CAR, ID));

	}

	@InjectMocks
	private CarService service;

	@Mock
	private CarRepo repo;

}
