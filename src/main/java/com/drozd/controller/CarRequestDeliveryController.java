package com.drozd.controller;

import com.drozd.forms.CarDataForm;
import com.drozd.forms.CarDeliveryRequestForm;
import com.drozd.persistence.models.Car;
import com.drozd.persistence.models.CarDeliveryRequest;
import com.drozd.persistence.models.Person;
import com.drozd.persistence.repository.CarRepository;
import com.drozd.persistence.repository.CarRequestDeliveryRepository;
import com.drozd.service.PersonService;
import com.drozd.support.enums.SideTab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static com.drozd.controller.LeaseSubjectController.ADD_LEASE_SUBJECT_VIEW;

@Controller
@Secured("ROLE_USER")
@RequestMapping("/carRequest")
public class CarRequestDeliveryController {

    private final static String ADD_CAR_REQUEST_DELIVERY_VIEW = "carRequest/carRequestDelivery";

    private final static String ALL_REQUESTS_VIEW = "carRequest/allRequests";

    @Autowired
    private PersonService personService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarRequestDeliveryRepository requestRepository;

    @RequestMapping(value = "/carRequestDelivery", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String addDeliveryRequest(Principal principal, Model model) {
        Person person = personService.getPersonByEmail(principal.getName());
        model.addAttribute("person",  person);
        model.addAttribute("sideTab", SideTab.REQUEST.getCode());
        List<Car> cars = carRepository.getCarsByPerson(person);
        model.addAttribute("cars", cars);
        if (cars.isEmpty()){
            return "redirect:/leaseSubject/addLeaseSubject";
        }
        model.addAttribute("requestForm", new CarDeliveryRequestForm());
        return ADD_CAR_REQUEST_DELIVERY_VIEW;
    }

    @RequestMapping(value = "/carRequestDelivery", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String addDeliveryRequest(@Valid @ModelAttribute CarDeliveryRequestForm requestForm, Principal principal, Model model) {
        Person person = personService.getPersonByEmail(principal.getName());
        model.addAttribute("person",  person);
        model.addAttribute("sideTab", SideTab.REQUEST.getCode());
        List<Car> cars = carRepository.getCarsByPerson(person);
        model.addAttribute("cars", cars);
        if (cars.isEmpty()){
            return ADD_LEASE_SUBJECT_VIEW;
        }
        Car car = carRepository.getCarsById(requestForm.getCarId());
        requestRepository.save(requestForm.createRequest(car));
        return "home/home";
    }

    @RequestMapping(value = "/myRequests", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String showMyRequests(@Valid @ModelAttribute CarDeliveryRequestForm requestForm, Principal principal, Model model) {
        Person person = personService.getPersonByEmail(principal.getName());
        model.addAttribute("person",  person);
        List<CarDeliveryRequest> requests = requestRepository.getAllRequestsByPerson(person);
        if (requests.isEmpty()) {
            return ADD_CAR_REQUEST_DELIVERY_VIEW;
        }
        model.addAttribute("requests", requests);
        return "home/home";
    }
}
