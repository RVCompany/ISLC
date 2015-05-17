package com.drozd.controller;

import java.security.Principal;

import com.drozd.persistence.repository.AccountRepository;
import com.drozd.persistence.models.Person;
import com.drozd.persistence.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal, Model model) {
        if (principal != null) {
            Person person = personRepository.findByAccount(accountRepository.findByEmail(principal.getName()));
            model.addAttribute("person",  person != null ? person : new Person());
            return "home/homeSignedIn";
        }
		return "home/homeNotSignedIn";
	}
}
