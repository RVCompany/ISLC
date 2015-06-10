package com.drozd.controller;

import com.drozd.forms.CarDataForm;
import com.drozd.persistence.models.Car;
import com.drozd.persistence.models.Person;
import com.drozd.service.CarAttributeService;
import com.drozd.service.CarService;
import com.drozd.service.PersonService;
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

import static com.drozd.persistence.models.Account.ROLE_USER;

@Controller
@Secured(ROLE_USER)
@RequestMapping(CarController.CAR_MAIN_RM)
public class CarController {

    private final static String CARS_TABLE_VIEW = "cars/carsTable";
    private final static String ADD_CAR_VIEW = "cars/add";

    public final static String CARS_TABLE_RM = "/carsTable";
    public final static String ADD_CAR_RM = "/add";

    public final static String CAR_MAIN_RM = "/cars";

    @Autowired
    private PersonService personService;

    @Autowired
    private CarService carService;

    @Autowired
    private CarAttributeService carAttributeService;


    @RequestMapping(value = ADD_CAR_RM, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String showAddCarView(Principal principal, Model model, CarDataForm carDataForm) {
        model.addAttribute("person", personService.getPersonByEmail(principal.getName()));
        model.addAttribute("allAttributes", carAttributeService.getAllAttributes());
        model.addAttribute("carDataForm", carDataForm);
        model.addAttribute("isCarsTab", true);
        return ADD_CAR_VIEW;
    }

    @RequestMapping(value = ADD_CAR_RM, method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute CarDataForm carDataForm, Errors errors, Model model, Principal principal,
                      RedirectAttributes ra) {
        Person person = personService.getPersonByEmail(principal.getName());
        model.addAttribute("person", person);
        if (errors.hasErrors()) {
            return ADD_CAR_VIEW;
        }
        List<String> attributeValueIds = carDataForm.getAttributeValueIds();
        Car car = carDataForm.createCar(carAttributeService.getValuesByIds(attributeValueIds), person);
        carService.save(car);
        model.addAttribute("cars", carService.getCarsByPerson(person));
        model.addAttribute("isCarsTab", true);
        return CARS_TABLE_VIEW;
    }

    @RequestMapping(value = CARS_TABLE_RM, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String leaseSubjectTable(Principal principal, Model model) {
        Person person = personService.getPersonByEmail(principal.getName());
        model.addAttribute("person", person);
        model.addAttribute("cars", carService.getCarsByPerson(person));
        model.addAttribute("isCarsTab", true);
        return CARS_TABLE_VIEW;
    }
}
