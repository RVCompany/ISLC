package com.drozd.forms;

import org.hibernate.validator.constraints.NotBlank;

public class CarDataForm {

    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    @NotBlank(message = CarDataForm.NOT_BLANK_MESSAGE)
    private Double price;

    private boolean customs;

    @NotBlank(message = CarDataForm.NOT_BLANK_MESSAGE)
    private Double fuelConsumption;

    @NotBlank(message = CarDataForm.NOT_BLANK_MESSAGE)
    private Double enginePower;

    @NotBlank(message = CarDataForm.NOT_BLANK_MESSAGE)
    private Integer race;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
}
