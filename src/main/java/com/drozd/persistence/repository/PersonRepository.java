package com.drozd.persistence.repository;

import com.drozd.persistence.models.Account;
import com.drozd.persistence.models.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@Repository
@Transactional(readOnly = true)
public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Person person) {
        entityManager.persist(person);
    }

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
