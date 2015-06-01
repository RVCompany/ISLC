package com.drozd.persistence.repository;

import com.drozd.persistence.models.Account;
import com.drozd.persistence.models.Car;
import com.drozd.persistence.models.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CarRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Car car) {
        entityManager.persist(car);
    }


    public void removeCar (Car car) {
        entityManager.remove(entityManager.getReference(Car.class,car.getCarId()));
    }

    public void updateCar (Car car){
        entityManager.merge(car);
    }

 /*   @Transactional
    public List getCars(String search) {
        if (null == search || search.trim().isEmpty()) {
            return entityManager.createQuery(
                    "select c from car c")
                    .getResultList();
        }
        return entityManager.createQuery(
                "select c from car c where c.name like :search")
                .setParameter("search", search.trim() + "%")
                .getResultList();
    }

    public List<Car> findByCar(String name, Long price) {
        return entityManager.createQuery(
                "select c from car c where c.name = :name and c.price = :price")
                .setParameter("name", name)
                .setParameter("price", price)
                .getResultList();
    }
}
*/

	public Person findByAccount(Account account) {
		try {
			return entityManager.createNamedQuery(Person.FIND_BY_ACCOUNT, Person.class)
					.setParameter("account", account)
					.getSingleResult();
		} catch (PersistenceException e) {
			return null;
		}
	}


}
