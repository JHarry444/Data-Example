package com.qa.data.example.rest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.data.example.controllers.CarController;
import com.qa.data.example.persistence.domain.Car;
import com.qa.data.example.services.CarService;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
@AutoConfigureWebMvc
public class CarControllerIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	@MockBean
	private CarService service;

	private final Car CAR = new Car("prius", 44.94);

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testGetAll() throws Exception {
		List<Car> mockList = new ArrayList<>();
		mockList.add(CAR);
		ResponseEntity<List<Car>> testResponse = ResponseEntity.ok(mockList);
		Mockito.when(this.service.findAll()).thenReturn(testResponse);
		this.mockMVC.perform(get("/car/getAll")).andExpect(status().isOk())
				.andExpect(content().string(containsString("prius"))).andDo(print());
	}

	@Test
	public void testCreateCar() throws Exception {
		Car testCar = new Car(CAR.getModel(), CAR.getPrice());
		testCar.setId(1L);
		ResponseEntity<Car> testResponse = ResponseEntity.ok(testCar);
		Mockito.when(service.createCar(Mockito.any(Car.class))).thenReturn(testResponse);

		mockMVC.perform(post("/car/create").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(CAR))).andExpect(status().isOk()).andDo(print()).andReturn();
	}
}
