package com.drozd.controller;

import java.security.Principal;
import java.util.List;

import com.drozd.forms.CarDeliveryRequestForm;
import com.drozd.persistence.models.CarAttribute;
import com.drozd.persistence.models.CarAttributeValue;
import com.drozd.persistence.models.CarDeliveryRequest;
import com.drozd.persistence.repository.AccountRepository;
import com.drozd.persistence.models.Person;
import com.drozd.persistence.repository.CarAttributeRepository;
import com.drozd.persistence.repository.CarAttributeValueRepository;
import com.drozd.persistence.repository.CarRepository;
import com.drozd.persistence.repository.CarRequestDeliveryRepository;
import com.drozd.persistence.repository.PersonRepository;
import com.drozd.service.PersonService;
import com.drozd.support.enums.SideTab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CarAttributeRepository carAttributeRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private CarRequestDeliveryRepository requestRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Principal principal, Model model) {
        if (principal != null) {
            Person person = personRepository.findByAccount(accountRepository.findByEmail(principal.getName()));
            model.addAttribute("person", person);
        }
        List<CarDeliveryRequest> requests = requestRepository.getAllRequests();
        model.addAttribute("requests", requests);
        return "home/home";
    }
}
