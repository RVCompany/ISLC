package com.drozd.persistence.repository;

import com.drozd.persistence.models.Car;
import com.drozd.persistence.models.CarAttribute;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class CarRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Car save(Car car) {
		entityManager.persist(car);
		return car;
	}
	
}
