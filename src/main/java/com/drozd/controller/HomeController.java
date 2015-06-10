package com.drozd.controller;

import java.security.Principal;
import java.util.List;

import com.drozd.persistence.models.CarDeliveryRequest;
import com.drozd.persistence.repository.AccountRepository;
import com.drozd.persistence.models.Person;
import com.drozd.persistence.repository.CarAttributeRepository;
import com.drozd.persistence.repository.CarDeliveryRequestRepository;
import com.drozd.persistence.repository.PersonRepository;
import com.drozd.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.drozd.controller.RequestController.ALL_DELIVERY_REQUESTS_VIEW;

@Controller
public class HomeController {

    @Autowired
    private PersonService personService;

    @Autowired
    private CarDeliveryRequestRepository requestRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Principal principal, Model model) {
        if (principal != null) {
            Person person = personService.getPersonByEmail(principal.getName());
            model.addAttribute("person",  person);
        }
        List<CarDeliveryRequest> requests = requestRepository.getAllRequests();
        model.addAttribute("requests", requests);
        return ALL_DELIVERY_REQUESTS_VIEW;
    }
}
