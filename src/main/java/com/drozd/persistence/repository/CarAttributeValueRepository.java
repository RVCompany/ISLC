package com.drozd.persistence.repository;

import com.drozd.persistence.models.Account;
import com.drozd.persistence.models.Car;
import com.drozd.persistence.models.CarAttributeValue;
import com.drozd.persistence.models.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CarAttributeValueRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(CarAttributeValue carAttribute) {
        entityManager.persist(carAttribute);
    }


    public void removeCarAttributeValue (CarAttributeValue carAttribute) {
        entityManager.remove(entityManager.getReference(CarAttributeValue.class,carAttribute.getCarAttributeValueId()));
    }

    public void updateCar (CarAttributeValue carAttribute){
        entityManager.merge(carAttribute);
    }

 @Transactional
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



	}



