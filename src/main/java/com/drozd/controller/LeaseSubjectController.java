package com.drozd.controller;

import com.drozd.persistence.models.Person;
import com.drozd.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.Principal;

@Controller
@Secured("ROLE_USER")
@RequestMapping("/leaseSubject")
public class LeaseSubjectController {

    private final static String ADD_LEASE_SUBJECT_VIEW = "leaseSubject/addLeaseSubject";

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/addLeaseSubject", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String addLeaseSubject(Principal principal, Model model) {
        Person person = personService.getPersonByEmail(principal.getName());
        model.addAttribute("person",  person != null ? person : new Person());
        return ADD_LEASE_SUBJECT_VIEW;
    }
}
