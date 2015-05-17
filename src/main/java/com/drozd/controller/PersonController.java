package com.drozd.controller;

import com.drozd.persistence.models.Account;
import com.drozd.persistence.repository.AccountRepository;
import com.drozd.persistence.models.Person;
import com.drozd.persistence.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@Secured("ROLE_USER")
class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AccountRepository accountRepository;

    private static final String PERSONAL_DATA_VIEW = "personalData/personalData";

    @RequestMapping(value = "personalData", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String getPersonalData(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Account account = accountRepository.findByEmail(auth.getName());

        Person person = personRepository.findByAccount(account);

        model.addAttribute("person", person == null ? new Person() : person);
        model.addAttribute("account", account);
        return PERSONAL_DATA_VIEW;
    }
}
