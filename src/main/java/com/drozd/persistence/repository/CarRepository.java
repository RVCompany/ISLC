package com.drozd.persistence.repository;

import com.drozd.persistence.models.Car;
import com.drozd.persistence.models.CarAttribute;
import com.drozd.persistence.models.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    public Car getCarsById(Long carId) {
        return entityManager.createQuery("select car from Car car where car.carId = :carId", Car.class)
                .setParameter("carId", carId)
                .getSingleResult();
    }

    public List<Car> getCarsByPerson(Person person) {
        return entityManager.createQuery("select car from Car car where car.person = :person", Car.class)
                .setParameter("person", person)
                .getResultList();
    }
	
}
