package com.drozd.service;

import com.drozd.persistence.models.Account;
import com.drozd.persistence.models.Person;
import com.drozd.persistence.repository.AccountRepository;
import com.drozd.persistence.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static com.drozd.support.PersonHelper.*;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    public Person getPersonByEmail(String email) {
        Account account = accountRepository.findByEmail(email);
        Person person = personRepository.findByAccount(account);
        return person;
    }

    @PostConstruct
    protected void initialize() {
        personRepository.save(getAdminPerson(accountRepository.findByEmail(ADMIN_EMAIL)));
        personRepository.save(getDefaultUserPerson(accountRepository.findByEmail(DEMO_USER_EMAIL)));
    }
}
