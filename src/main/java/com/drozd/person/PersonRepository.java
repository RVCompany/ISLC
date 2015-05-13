package com.drozd.person;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Person person) {
        entityManager.persist(person);
    }

/*	public Account findByEmail(String email) {
		try {
			return entityManager.createNamedQuery(Account.FIND_BY_EMAIL, Account.class)
					.setParameter("email", email)
					.getSingleResult();
		} catch (PersistenceException e) {
			return null;
		}
	}*/


}
