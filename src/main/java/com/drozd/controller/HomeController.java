package com.drozd.controller;

import java.security.Principal;
import java.util.List;

import com.drozd.persistence.models.CarAttribute;
import com.drozd.persistence.models.CarAttributeValue;
import com.drozd.persistence.repository.AccountRepository;
import com.drozd.persistence.models.Person;
import com.drozd.persistence.repository.CarAttributeRepository;
import com.drozd.persistence.repository.CarAttributeValueRepository;
import com.drozd.persistence.repository.PersonRepository;
import com.drozd.support.enums.SideTab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CarAttributeRepository carAttributeRepository;

    @Autowired
    private CarAttributeValueRepository carAttributeValueRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Principal principal, Model model) {
        if (principal != null) {
            Person person = personRepository.findByAccount(accountRepository.findByEmail(principal.getName()));
            model.addAttribute("person", person != null ? person : new Person());
        }
        return "home/home";
    }

    /*TODO: Remake using import.sql*/
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(Principal principal, Model model) {
        if (principal != null) {
            Person person = personRepository.findByAccount(accountRepository.findByEmail(principal.getName()));
            model.addAttribute("person", person != null ? person : new Person());

            CarAttribute carAttribute = carAttributeRepository.save(new CarAttribute("Тип кузова"));
            CarAttribute carAttribute2 = carAttributeRepository.save(new CarAttribute("att2"));
            CarAttribute carAttribute3 = carAttributeRepository.save(new CarAttribute("att3"));
            CarAttribute carAttribute4 = carAttributeRepository.save(new CarAttribute("att4"));

            CarAttributeValue carAttributeValue11 = new CarAttributeValue(carAttribute, "Хетчбек");
            CarAttributeValue carAttributeValue12 = new CarAttributeValue(carAttribute, "Седан");
            CarAttributeValue carAttributeValue13 = new CarAttributeValue(carAttribute, "Універсал");
            CarAttributeValue carAttributeValue14 = new CarAttributeValue(carAttribute, "Купе");
            CarAttributeValue carAttributeValue15 = new CarAttributeValue(carAttribute, "Лімузин");
            CarAttributeValue carAttributeValue16 = new CarAttributeValue(carAttribute, "Мінівен");

            CarAttributeValue carAttributeValue21 = new CarAttributeValue(carAttribute2, "atr2val1");
            CarAttributeValue carAttributeValue22 = new CarAttributeValue(carAttribute2, "atr2val2");
            CarAttributeValue carAttributeValue23 = new CarAttributeValue(carAttribute2, "atr2val3");
            CarAttributeValue carAttributeValue24 = new CarAttributeValue(carAttribute2, "atr2val4");

            CarAttributeValue carAttributeValue31 = new CarAttributeValue(carAttribute3, "atr3val1");
            CarAttributeValue carAttributeValue32 = new CarAttributeValue(carAttribute3, "atr3val2");
            CarAttributeValue carAttributeValue33 = new CarAttributeValue(carAttribute3, "atr3val3");
            CarAttributeValue carAttributeValue34 = new CarAttributeValue(carAttribute3, "atr3val4");

            CarAttributeValue carAttributeValue41 = new CarAttributeValue(carAttribute4, "atr4val1");
            CarAttributeValue carAttributeValue42 = new CarAttributeValue(carAttribute4, "atr4val2");
            CarAttributeValue carAttributeValue43 = new CarAttributeValue(carAttribute4, "atr4val3");
            CarAttributeValue carAttributeValue44 = new CarAttributeValue(carAttribute4, "atr4val4");


            carAttributeValueRepository.save(carAttributeValue11);
            carAttributeValueRepository.save(carAttributeValue12);
            carAttributeValueRepository.save(carAttributeValue13);
            carAttributeValueRepository.save(carAttributeValue14);
            carAttributeValueRepository.save(carAttributeValue15);
            carAttributeValueRepository.save(carAttributeValue16);

            carAttributeValueRepository.save(carAttributeValue21);
            carAttributeValueRepository.save(carAttributeValue22);
            carAttributeValueRepository.save(carAttributeValue23);
            carAttributeValueRepository.save(carAttributeValue24);

            carAttributeValueRepository.save(carAttributeValue31);
            carAttributeValueRepository.save(carAttributeValue32);
            carAttributeValueRepository.save(carAttributeValue33);
            carAttributeValueRepository.save(carAttributeValue34);

            carAttributeValueRepository.save(carAttributeValue41);
            carAttributeValueRepository.save(carAttributeValue42);
            carAttributeValueRepository.save(carAttributeValue43);
            carAttributeValueRepository.save(carAttributeValue44);

            return "home/homeSignedIn";
        }
        return "home/homeNotSignedIn";
    }
}
