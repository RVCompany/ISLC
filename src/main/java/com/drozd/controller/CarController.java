package com.drozd.controller;

import com.drozd.forms.CarDataForm;
import com.drozd.persistence.models.Car;
import com.drozd.persistence.models.Person;
import com.drozd.persistence.repository.CarRepository;
import com.drozd.service.CarAttributeService;
import com.drozd.service.CarService;
import com.drozd.service.PersonService;
import com.drozd.support.enums.SideTab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@Secured("ROLE_USER")
@RequestMapping("/leaseSubject")
public class CarController {

    private final static String LEASE_SUBJECT_TABLE_VIEW = "leaseSubject/leaseSubjectTable";
    public final static String ADD_LEASE_SUBJECT_VIEW = "leaseSubject/addLeaseSubject";

    @Autowired
    private PersonService personService;

    @Autowired
    private CarService carService;

    @Autowired
    private CarAttributeService carAttributeService;


    @RequestMapping(value = "/addLeaseSubject", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String addLeaseSubject(Principal principal, Model model, CarDataForm carDataForm) {
        model.addAttribute("person", personService.getPersonByEmail(principal.getName()));
        model.addAttribute("allAttributes", carAttributeService.getAllAttributes());
        model.addAttribute("carDataForm", carDataForm);
        model.addAttribute("sideTab", SideTab.LEASE_SUBJECT.getCode());

        return ADD_LEASE_SUBJECT_VIEW;
    }

    @RequestMapping(value = "/leaseSubjectTable", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String leaseSubjectTable(Principal principal, Model model) {
        Person person = personService.getPersonByEmail(principal.getName());
        model.addAttribute("person", person);
        model.addAttribute("sideTab", SideTab.LEASE_SUBJECT.getCode());
        model.addAttribute("cars", carService.getCarsByPerson(person));
        return LEASE_SUBJECT_TABLE_VIEW;
    }

    @RequestMapping(value = "/addLeaseSubject", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute CarDataForm carDataForm, Errors errors, Model model, Principal principal,
                      RedirectAttributes ra) {
        Person person = personService.getPersonByEmail(principal.getName());
        if (errors.hasErrors()) {
            return ADD_LEASE_SUBJECT_VIEW;
        }
        List<String> attributeValueIds = carDataForm.getAttributeValueIds();
        Car car = carDataForm.createCar(carAttributeService.getValuesByIds(attributeValueIds), person);
        carService.save(car);
        model.addAttribute("cars", carService.getCarsByPerson(person));
        return LEASE_SUBJECT_TABLE_VIEW;
    }

}
