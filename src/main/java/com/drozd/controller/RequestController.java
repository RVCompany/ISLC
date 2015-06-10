package com.drozd.controller;

import com.drozd.forms.CarDeliveryRequestForm;
import com.drozd.persistence.models.Car;
import com.drozd.persistence.models.CarDeliveryRequest;
import com.drozd.persistence.models.Person;
import com.drozd.persistence.repository.CarDeliveryRequestRepository;
import com.drozd.persistence.repository.CarRepository;
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

import static com.drozd.persistence.models.Account.*;

@Controller
@RequestMapping(RequestController.DELIVERY_REQUESTS_MAIN_RM)
public class RequestController {

    private final static String ADD_REQUEST_VIEW = "deliveryRequests/add";

    private final static String MY_REQUESTS_VIEW = "deliveryRequests/myRequests";

    private final static String ADD_REQUEST_RM = "/add";

    private final static String ALL_REQUESTS_RM = "/allRequests";

    private final static String REDIRECT = "redirect:";

    private final static String MY_REQUESTS_RM = "/myRequests";

    public final static String ALL_DELIVERY_REQUESTS_VIEW = "deliveryRequests/allRequests";

    public final static String DELIVERY_REQUESTS_MAIN_RM = "/deliveryRequests";

    @Autowired
    private PersonService personService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarDeliveryRequestRepository requestRepository;

    @Secured(ROLE_USER)
    @RequestMapping(value = ADD_REQUEST_RM, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String showAddView(Principal principal, Model model) {
        Person person = personService.getPersonByEmail(principal.getName());
        model.addAttribute("person",  person);
        List<Car> cars = carRepository.getCarsByPerson(person);
        model.addAttribute("cars", cars);
        if (cars.isEmpty()){
            return REDIRECT + CarController.CAR_MAIN_RM + CarController.ADD_CAR_RM;
        }
        model.addAttribute("carDeliveryRequestForm", new CarDeliveryRequestForm());
        model.addAttribute("isDeliveryRequestTab", true);
        return ADD_REQUEST_VIEW;
    }

    @Secured(ROLE_USER)
    @RequestMapping(value = ADD_REQUEST_RM, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String add(@Valid @ModelAttribute CarDeliveryRequestForm carDeliveryRequestForm,  Errors errors,
                                     Principal principal, Model model, RedirectAttributes ra) {
        Person person = personService.getPersonByEmail(principal.getName());
        model.addAttribute("person",  person);
        model.addAttribute("isDeliveryRequestTab", true);
        if (errors.hasErrors()) {
            List<Car> cars = carRepository.getCarsByPerson(person);
            model.addAttribute("cars", cars);
            return ADD_REQUEST_VIEW;
        }
        Car car = carRepository.getCarsById(carDeliveryRequestForm.getCarId());
        requestRepository.save(carDeliveryRequestForm.createRequest(car));
        return REDIRECT + DELIVERY_REQUESTS_MAIN_RM + MY_REQUESTS_RM;
    }

    @Secured(ROLE_USER)
    @RequestMapping(value = MY_REQUESTS_RM, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String showMyRequests(Principal principal, Model model) {
        Person person = personService.getPersonByEmail(principal.getName());
        model.addAttribute("person",  person);
        List<CarDeliveryRequest> requests = requestRepository.getAllRequestsByPerson(person);
        if (requests.isEmpty()) {
            return REDIRECT + DELIVERY_REQUESTS_MAIN_RM + ADD_REQUEST_RM;
        }
        model.addAttribute("requests", requests);
        model.addAttribute("isDeliveryRequestTab", true);
        return MY_REQUESTS_VIEW;
    }

    @RequestMapping(value = ALL_REQUESTS_RM, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String showAllRequests(Principal principal, Model model) {
        if (principal != null) {
            Person person = personService.getPersonByEmail(principal.getName());
            model.addAttribute("person",  person);
        }
        List<CarDeliveryRequest> requests = requestRepository.getAllRequests();
        model.addAttribute("requests", requests);
        model.addAttribute("isDeliveryRequestTab", true);
        return ALL_DELIVERY_REQUESTS_VIEW;
    }
}
