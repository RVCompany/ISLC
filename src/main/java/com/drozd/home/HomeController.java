package com.drozd.home;

import java.security.Principal;

import com.drozd.account.AccountRepository;
import com.drozd.person.Person;
import com.drozd.person.PersonRepository;
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
