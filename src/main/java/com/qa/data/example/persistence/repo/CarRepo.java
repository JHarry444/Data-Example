package com.qa.data.example.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.data.example.persistence.domain.Car;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

}
