package com.drozd.service;

import com.drozd.persistence.models.CarAttribute;
import com.drozd.persistence.models.CarAttributeValue;
import com.drozd.persistence.repository.CarAttributeRepository;
import com.drozd.persistence.repository.CarAttributeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CarAttributeValueService {

    @Autowired
    private CarAttributeRepository carAttributeRepository;

    public void initAttributesAndValues() {

        CarAttribute carAttributeBodyType = carAttributeRepository.save(new CarAttribute("Тип кузова"));
        CarAttribute carAttributeKPPType = carAttributeRepository.save(new CarAttribute("Тип коробки передач"));
        CarAttribute carAttributeHandleType = carAttributeRepository.save(new CarAttribute("Тип приводу"));
        CarAttribute carAttributeFuelType = carAttributeRepository.save(new CarAttribute("Тип пального"));

        CarAttributeValue carAttributeValueBT11 = new CarAttributeValue(carAttributeBodyType, "Хетчбек");
        CarAttributeValue carAttributeValueBT12 = new CarAttributeValue(carAttributeBodyType, "Седан");
        CarAttributeValue carAttributeValueBT13 = new CarAttributeValue(carAttributeBodyType, "Універсал");
        CarAttributeValue carAttributeValueBT14 = new CarAttributeValue(carAttributeBodyType, "Купе");
        CarAttributeValue carAttributeValueBT15 = new CarAttributeValue(carAttributeBodyType, "Лімузин");
        CarAttributeValue carAttributeValueBT16 = new CarAttributeValue(carAttributeBodyType, "Мінівен");
        CarAttributeValue carAttributeValueBT17 = new CarAttributeValue(carAttributeBodyType, "Фургон");
        CarAttributeValue carAttributeValueBT18 = new CarAttributeValue(carAttributeBodyType, "Кабріолет");
        CarAttributeValue carAttributeValueBT19 = new CarAttributeValue(carAttributeBodyType, "Пікап");

        CarAttributeValue carAttributeValueKPP11 = new CarAttributeValue(carAttributeKPPType, "Автомат");
        CarAttributeValue carAttributeValueKPP12 = new CarAttributeValue(carAttributeKPPType, "Типтронік");
        CarAttributeValue carAttributeValueKPP13 = new CarAttributeValue(carAttributeKPPType, "Механічна");
        CarAttributeValue carAttributeValueKPP14 = new CarAttributeValue(carAttributeKPPType, "Напівавтомат");

        CarAttributeValue carAttributeValueHT11 = new CarAttributeValue(carAttributeHandleType, "Повний");
        CarAttributeValue carAttributeValueHT12 = new CarAttributeValue(carAttributeHandleType, "Передній");
        CarAttributeValue carAttributeValueHT13 = new CarAttributeValue(carAttributeHandleType, "Задній");


        CarAttributeValue carAttributeValueFT11 = new CarAttributeValue(carAttributeFuelType, "Бензин");
        CarAttributeValue carAttributeValueFT12 = new CarAttributeValue(carAttributeFuelType, "Дизель");
        CarAttributeValue carAttributeValueFT13 = new CarAttributeValue(carAttributeFuelType, "Газ");
        CarAttributeValue carAttributeValueFT14 = new CarAttributeValue(carAttributeFuelType, "Гібрид");
        CarAttributeValue carAttributeValueFT15 = new CarAttributeValue(carAttributeFuelType, "Електро");
        CarAttributeValue carAttributeValueFT16 = new CarAttributeValue(carAttributeFuelType, "Газ/бензин");
        CarAttributeValue carAttributeValueFT17 = new CarAttributeValue(carAttributeFuelType, "Інше");

        carAttributeValueRepository.save(carAttributeValueBT11);
        carAttributeValueRepository.save(carAttributeValueBT12);
        carAttributeValueRepository.save(carAttributeValueBT13);
        carAttributeValueRepository.save(carAttributeValueBT14);
        carAttributeValueRepository.save(carAttributeValueBT15);
        carAttributeValueRepository.save(carAttributeValueBT16);
        carAttributeValueRepository.save(carAttributeValueBT17);
        carAttributeValueRepository.save(carAttributeValueBT18);
        carAttributeValueRepository.save(carAttributeValueBT19);

        carAttributeValueRepository.save(carAttributeValueKPP11);
        carAttributeValueRepository.save(carAttributeValueKPP12);
        carAttributeValueRepository.save(carAttributeValueKPP13);
        carAttributeValueRepository.save(carAttributeValueKPP14);

        carAttributeValueRepository.save(carAttributeValueHT11);
        carAttributeValueRepository.save(carAttributeValueHT12);
        carAttributeValueRepository.save(carAttributeValueHT13);

        carAttributeValueRepository.save(carAttributeValueFT11);
        carAttributeValueRepository.save(carAttributeValueFT12);
        carAttributeValueRepository.save(carAttributeValueFT13);
        carAttributeValueRepository.save(carAttributeValueFT14);
        carAttributeValueRepository.save(carAttributeValueFT15);
        carAttributeValueRepository.save(carAttributeValueFT16);
        carAttributeValueRepository.save(carAttributeValueFT17);
    }

    @Autowired
    private CarAttributeValueRepository carAttributeValueRepository;

    public Set<CarAttributeValue> getValuesByIds(List<String> ids) {
        Set<CarAttributeValue> values = new HashSet<>();
        for (String id : ids) {
            if (id != null) {
                values.add(carAttributeValueRepository.getById(Long.parseLong(id)));
            }
        }
        return values;
    }
}
