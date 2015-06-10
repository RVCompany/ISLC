package com.drozd.persistence.repository;

import com.drozd.persistence.models.CarDeliveryRequest;
import com.drozd.persistence.models.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CarDeliveryRequestRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public CarDeliveryRequest save(CarDeliveryRequest request) {
        entityManager.persist(request);
        return request;
    }

    @Transactional
    public CarDeliveryRequest update(CarDeliveryRequest request) {
        entityManager.refresh(request);
        return request;
    }

    public List<CarDeliveryRequest> getAllRequestsByPerson(Person person) {
        return entityManager.createQuery("SELECT crd FROM CarDeliveryRequest crd where crd.car.person = :person")
                .setParameter("person", person)
                .getResultList();
    }

    public List<CarDeliveryRequest> getAllRequests() {
        return entityManager.createQuery("SELECT crd FROM CarDeliveryRequest crd").getResultList();
    }
}
