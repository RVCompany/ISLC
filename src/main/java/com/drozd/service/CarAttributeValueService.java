package com.drozd.service;

import com.drozd.persistence.models.CarAttributeValue;
import com.drozd.persistence.repository.CarAttributeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CarAttributeValueService {

    @Autowired
    private CarAttributeValueRepository carAttributeValueRepository;

    public Set<CarAttributeValue> getValuesByIds(List<String> ids){
        Set<CarAttributeValue> values = new HashSet<>();
        for (String id : ids) {
            if(id != null) {
                values.add(carAttributeValueRepository.getById(Long.parseLong(id)));
            }
        }
        return values;
    }
}
