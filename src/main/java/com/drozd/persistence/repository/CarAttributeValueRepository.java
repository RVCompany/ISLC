package com.drozd.persistence.repository;

import com.drozd.persistence.models.CarAttributeValue;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@Repository
@Transactional(readOnly = true)
public class CarAttributeValueRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public CarAttributeValue save(CarAttributeValue carAttributeValue) {
		entityManager.persist(carAttributeValue);
		return carAttributeValue;
	}

    public CarAttributeValue getById(Long id) {
        try {
            return entityManager.createNamedQuery(CarAttributeValue.FIND_BY_ID, CarAttributeValue.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
        catch (PersistenceException e){
            return null;
        }
    }

/*    public List<CarAttributeValue> getAllByAttributeId(Long caId) {
        return entityManager.createQuery("select cav from CarAttributeValue cav where cav.carAttribute.id = :caId",
                CarAttributeValue.class).setParameter("caId", caId).getResultList();
    }*/
	
}
