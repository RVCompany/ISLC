package com.drozd.service;

import com.drozd.persistence.models.Car;
import com.drozd.persistence.models.CarAttribute;
import com.drozd.persistence.models.CarAttributeValue;
import com.drozd.persistence.models.Person;
import com.drozd.persistence.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.drozd.support.NumberUtils.randInt;
import static com.drozd.support.PersonHelper.*;

@Service
public class CarService {

    @Autowired
    private PersonService personService;

    @Autowired
    private CarAttributeService carAttributeService;

    @Autowired
    private CarRepository carRepository;

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public Car getCarsById(Long carId) {
        return carRepository.getCarsById(carId);
    }

    public List<Car> getCarsByPerson(Person person) {
        return carRepository.getCarsByPerson(person);
    }

    @PostConstruct
    public void initialize() {
        Person demoUser = personService.getPersonByEmail(DEMO_USER_EMAIL);

        Car car1 = new Car("Alpha Romeo", "S", randInt(10000, Integer.MAX_VALUE), true, 10.0, 2.0,
                randInt(0, 100000), randInt(1900, 2015), prepareValues(), demoUser);
        Car car2 = new Car("Audi", "A6", randInt(10000, Integer.MAX_VALUE), true, 10.0, 2.0,
                randInt(0, 100000), randInt(1900, 2015), prepareValues(), demoUser);
        Car car3 = new Car("Aston Martin", "SDF", randInt(10000, Integer.MAX_VALUE), true, 10.0, 2.0,
                randInt(0, 100000), randInt(1900, 2015), prepareValues(), demoUser);
        Car car4 = new Car("BMW", "3", randInt(10000, Integer.MAX_VALUE), true, 10.0, 2.0,
                randInt(0, 100000), randInt(1900, 2015), prepareValues(), demoUser);
        Car car5 = new Car("Jaguar", "S", randInt(10000, Integer.MAX_VALUE), true, 10.0, 2.0,
                randInt(0, 100000), randInt(1900, 2015), prepareValues(), demoUser);
        Car car6 = new Car("Maserati", "Laguna", randInt(10000, Integer.MAX_VALUE), true, 10.0, 2.0,
                randInt(0, 100000), randInt(1900, 2015), prepareValues(), demoUser);
        Car car7 = new Car("Lexus", "S6", randInt(10000, Integer.MAX_VALUE), true, 10.0, 2.0,
                randInt(0, 100000), randInt(1900, 2015), prepareValues(), demoUser);
        Car car8 = new Car("Opel", "Kadet", randInt(10000, Integer.MAX_VALUE), true, 10.0, 2.0,
                randInt(0, 100000), randInt(1900, 2015), prepareValues(), demoUser);
        Car car9 = new Car("Peugeot", "Kangoo", randInt(10000, Integer.MAX_VALUE), true, 10.0, 2.0,
                randInt(0, 100000), randInt(1900, 2015), prepareValues(), demoUser);
        Car car10 = new Car("Rolls-Royce", "SDX", randInt(10000, Integer.MAX_VALUE), true, 10.0, 2.0,
                randInt(0, 100000), randInt(1900, 2015), prepareValues(), demoUser);
        Car car11 = new Car("Renault", "Scenic", randInt(10000, Integer.MAX_VALUE), true, 10.0, 2.0,
                randInt(0, 100000), randInt(1900, 2015), prepareValues(), demoUser);
        Car car12 = new Car("Skoda", "Fabia", randInt(10000, Integer.MAX_VALUE), true, 10.0, 2.0,
                randInt(0, 100000), randInt(1900, 2015), prepareValues(), demoUser);
        Car car13 = new Car("Volkswagen", "Golf", randInt(10000, Integer.MAX_VALUE), true, 10.0, 2.0,
                randInt(0, 100000), randInt(1900, 2015), prepareValues(), demoUser);
        Car car14 = new Car("Volvo", "S6", randInt(10000, Integer.MAX_VALUE), true, 10.0, 2.0,
                randInt(0, 100000), randInt(1900, 2015), prepareValues(), demoUser);
        Car car15 = new Car("Ford", "Ascord", randInt(10000, Integer.MAX_VALUE), true, 10.0, 2.0,
                randInt(0, 100000), randInt(1900, 2015), prepareValues(), demoUser);
        Car car16 = new Car("Boqdan", "Beniuk", randInt(10000, Integer.MAX_VALUE), true, 10.0, 2.0,
                randInt(0, 100000), randInt(1900, 2015), prepareValues(), demoUser);
        Car car17 = new Car("ZAZ", "Zapor", randInt(10000, Integer.MAX_VALUE), true, 10.0, 2.0,
                randInt(0, 100000), randInt(1900, 2015), prepareValues(), demoUser);

        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);
        carRepository.save(car4);
        carRepository.save(car5);
        carRepository.save(car6);
        carRepository.save(car7);
        carRepository.save(car8);
        carRepository.save(car9);
        carRepository.save(car10);
        carRepository.save(car11);
        carRepository.save(car12);
        carRepository.save(car13);
        carRepository.save(car14);
        carRepository.save(car15);
        carRepository.save(car16);
        carRepository.save(car17);
    }

    private Set<CarAttributeValue> prepareValues() {
        Set<CarAttributeValue> values = new HashSet<>();
        List<CarAttribute> attributes = carAttributeService.getAllAttributes();
        for (CarAttribute attribute : attributes) {
            Set<CarAttributeValue> attrValues = attribute.getValues();
            if (!attrValues.isEmpty()){
                List<CarAttributeValue> attrValuesList = new ArrayList<>(attrValues);
                values.add(attrValuesList.get(randInt(0, attrValuesList.size()-1)));
            }
        }
        return values;
    }
}