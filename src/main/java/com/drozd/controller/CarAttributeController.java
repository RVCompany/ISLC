package com.drozd.controller;

import com.drozd.persistence.models.CarAttribute;
import com.drozd.persistence.models.CarAttributeValue;
import com.drozd.persistence.models.Firm;
import com.drozd.persistence.models.Person;

import javax.persistence.*;
import java.util.*;


public class CarAttributeController implements java.io.Serializable {

    CarAttribute Ca= new CarAttribute("body",Set<CarAttribute>(List<String>("s")));


}