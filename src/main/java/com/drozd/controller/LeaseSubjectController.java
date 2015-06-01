package com.drozd.controller;

import com.drozd.forms.CarDataForm;
import com.drozd.forms.SignupForm;
import com.drozd.persistence.models.Account;
import com.drozd.persistence.models.Car;
import com.drozd.persistence.models.CarAttribute;
import com.drozd.persistence.models.Person;
import com.drozd.persistence.repository.CarAttributeRepository;
import com.drozd.persistence.repository.CarAttributeValueRepository;
import com.drozd.persistence.repository.CarRepository;
import com.drozd.service.CarAttributeValueService;
import com.drozd.service.PersonService;
import com.drozd.support.enums.SideTab;
import com.drozd.support.web.MessageHelper;
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
import org.thymeleaf.expression.Messages;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@Secured("ROLE_USER")
@RequestMapping("/leaseSubject")
public class LeaseSubjectController {

    private final static String ADD_LEASE_SUBJECT_VIEW = "leaseSubject/addLeaseSubject";

    private static List<CarAttribute> allAttributes;

    @Autowired
    private PersonService personService;

    @Autowired
    private CarAttributeValueService carAttributeValueService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarAttributeRepository carAttributeRepository;

    @Autowired
    private CarAttributeValueRepository carAttributeValueRepository;


    @RequestMapping(value = "/addLeaseSubject", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String addLeaseSubject(Principal principal, Model model, CarDataForm carDataForm) {
        model.addAttribute("person", personService.getPersonByEmail(principal.getName()));
        model.addAttribute("allAttributes", getAllAttributes());
        model.addAttribute("carDataForm", carDataForm);
        model.addAttribute("sideTab", SideTab.LEASE_SUBJECT.getCode());

        return ADD_LEASE_SUBJECT_VIEW;
    }

    @RequestMapping(value = "/addLeaseSubject", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute CarDataForm carDataForm, Errors errors, Model model, Principal principal) {
        Person person = personService.getPersonByEmail(principal.getName());
        model.addAttribute("sideTab", SideTab.LEASE_SUBJECT.getCode());
        if (errors.hasErrors()) {
            model.addAttribute("person", person);
            model.addAttribute("allAttributes", getAllAttributes());
            return ADD_LEASE_SUBJECT_VIEW;
        }
        List<String> attributeValueIds = carDataForm.getAttributeValueIds();
        Car car = carDataForm.createCar(carAttributeValueService.getValuesByIds(attributeValueIds), person);
        carRepository.save(car);
        return "redirect:/";
    }

    private List<CarAttribute> getAllAttributes() {
        if (allAttributes == null || allAttributes.isEmpty()) {
            allAttributes = carAttributeRepository.getAllCarAttributes();
        }
        return allAttributes;
    }
}