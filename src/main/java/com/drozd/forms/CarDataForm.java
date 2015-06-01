package com.drozd.forms;

import com.drozd.persistence.models.Car;
import com.drozd.persistence.models.CarAttributeValue;
import com.drozd.persistence.models.Person;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CarDataForm {

    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    @NotBlank(message = NOT_BLANK_MESSAGE)
    @Size(min = 1, max = 20)
    private String brand;

    @Size(min = 1, max = 20)
    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String model;

    @NotNull
    @Min(0)
    private Integer price;

    private boolean customs;

    @NotNull
    @Min(0)
    @Max(100)
    private Double fuelConsumption;

    @NotNull
    @Min(0)
    @Max(5)
    private Double enginePower;

    @NotNull
    @Min(0)
    private Integer race;

    private Integer year;

    private List<String> attributeValueIds = new ArrayList<>(20);

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean isCustoms() {
        return customs;
    }

    public void setCustoms(boolean customs) {
        this.customs = customs;
    }

    public Double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public Double getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Double enginePower) {
        this.enginePower = enginePower;
    }

    public Integer getRace() {
        return race;
    }

    public void setRace(Integer race) {
        this.race = race;
    }

    public List<String> getAttributeValueIds() {
        return attributeValueIds;
    }

    public void setAttributeValueIds(List<String> attributeValueIds) {
        this.attributeValueIds = attributeValueIds;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Car createCar(Set<CarAttributeValue> attributeValues, Person person) {
        return new Car(brand, model, price, customs, fuelConsumption, enginePower, race, year, attributeValues, person);
    }
}
