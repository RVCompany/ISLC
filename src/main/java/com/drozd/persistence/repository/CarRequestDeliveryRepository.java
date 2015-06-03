package com.drozd.persistence.repository;

import com.drozd.persistence.models.CarAttribute;
import com.drozd.persistence.models.CarDeliveryRequest;
import com.drozd.persistence.models.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CarRequestDeliveryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public CarDeliveryRequest save(CarDeliveryRequest carAttribute) {
        entityManager.persist(carAttribute);
        return carAttribute;
    }

    public List<CarAttribute> getCarDeliveryRequests(Person person) {
        return entityManager.createQuery("SELECT crd FROM CarDeliveryRequest crd where crd.car.person = :person")
                .setParameter("person", person)
                .getResultList();
    }

    public List<CarDeliveryRequest> getAllCarDeliveryRequests() {
        return entityManager.createQuery("SELECT crd FROM CarDeliveryRequest crd").getResultList();
    }
}
