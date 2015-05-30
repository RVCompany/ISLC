package com.drozd.controller;

import com.drozd.forms.CarDataForm;
import com.drozd.persistence.models.CarAttribute;
import com.drozd.persistence.models.Person;
import com.drozd.persistence.repository.CarAttributeRepository;
import com.drozd.persistence.repository.CarAttributeValueRepository;
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
import java.util.List;

@Controller
@Secured("ROLE_USER")
@RequestMapping("/leaseSubject")
public class LeaseSubjectController {

    private final static String ADD_LEASE_SUBJECT_VIEW = "leaseSubject/addLeaseSubject";

    @Autowired
    private PersonService personService;

    @Autowired
    private CarAttributeRepository carAttributeRepository;

    @Autowired
    private CarAttributeValueRepository carAttributeValueRepository;


    @RequestMapping(value = "/addLeaseSubject", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String addLeaseSubject(Principal principal, Model model, CarDataForm carDataForm) {
        Person person = personService.getPersonByEmail(principal.getName());
        model.addAttribute("person",  person != null ? person : new Person());

        List<CarAttribute> allAttributes = carAttributeRepository.getAllCarAttributes();

        model.addAttribute("allAttributes", allAttributes);
        model.addAttribute("carDataForm", carDataForm);

        return ADD_LEASE_SUBJECT_VIEW;
    }
}
