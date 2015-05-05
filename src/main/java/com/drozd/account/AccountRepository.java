package com.drozd.account;

import javax.persistence.*;
import javax.inject.Inject;

import com.google.common.collect.Collections2;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class AccountRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public Account save(Account account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		entityManager.persist(account);
		return account;
	}
	
	public Account findByEmail(String email) {
		try {
			return entityManager.createNamedQuery(Account.FIND_BY_EMAIL, Account.class)
					.setParameter("email", email)
					.getSingleResult();
		} catch (PersistenceException e) {
			return null;
		}
	}


	public List<Account> getAllAccounts() {
		try {
			return entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList();
		} catch (PersistenceException e) {
			return new ArrayList<Account>();
		}
	}


}
