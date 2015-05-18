package com.drozd.service;

import com.drozd.persistence.models.Account;
import com.drozd.persistence.models.Person;
import com.drozd.persistence.repository.AccountRepository;
import com.drozd.persistence.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Person getPersonByEmail(String email) {
        Account account = accountRepository.findByEmail(email);
        Person person = personRepository.findByAccount(account);
        return person;
    }

}
