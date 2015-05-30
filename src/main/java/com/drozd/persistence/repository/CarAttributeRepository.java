package com.drozd.persistence.repository;

import com.drozd.persistence.models.CarAttribute;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CarAttributeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public CarAttribute save(CarAttribute carAttribute) {
        entityManager.persist(carAttribute);
        return carAttribute;
    }

    public List<CarAttribute> getAllCarAttributes() {
        return entityManager.createQuery("SELECT ca FROM CarAttribute ca").getResultList();
    }
}
