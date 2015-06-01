package com.drozd.persistence.repository;

import com.drozd.persistence.models.Account;
import com.drozd.persistence.models.CarAttribute;
import com.drozd.persistence.models.CarAttributeValue;
import com.drozd.persistence.models.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@Repository
@Transactional(readOnly = true)
public class CarAttributeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(CarAttribute carAttribute) {
        entityManager.persist(carAttribute);
    }


    public void removeCarAttributeValue (CarAttribute carAttribute) {
        entityManager.remove(entityManager.getReference(CarAttribute.class,carAttribute.getId()));
    }

    public void updateCar (CarAttributeValue carAttribute){
        entityManager.merge(carAttribute);
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
