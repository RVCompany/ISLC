package com.drozd.controller;

import com.drozd.persistence.models.Person;
import com.drozd.service.PersonService;
import com.drozd.support.enums.SideTab;
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
@RequestMapping("/carRequest")
public class CarRequestDeliveryController {

    private final static String ADD_CAR_REQUEST_DELIVERY_VIEW = "carRequest/carRequestDelivery";

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/carRequestDelivery", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String carRequestDelivery(Principal principal, Model model) {
        Person person = personService.getPersonByEmail(principal.getName());
        model.addAttribute("person",  person != null ? person : new Person());
        model.addAttribute("sideTab", SideTab.REQUEST.getCode());

        return ADD_CAR_REQUEST_DELIVERY_VIEW;
    }
}
