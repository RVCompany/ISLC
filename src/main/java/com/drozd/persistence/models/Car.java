package com.drozd.persistence.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
@Entity
@Table(name = "car")
public class Car implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "carId", unique = true, nullable = false)
    private Long carId;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private Integer price;

    @Column
    private boolean customs;

    @Column
    private Double fuelConsumption;

    @Column
    private Double enginePower;

    @Column
    private Integer race;

    @Column
    private Integer year;

    @Column
    private boolean inLease;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId", nullable = true)
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firmId", nullable = true)
    private Firm firm;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cars")
    private Set<CarAttributeValue> attributeValues = new HashSet<>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private Set<CarDeliveryRequest> requests;

    public Car() {
    }

    public Car(String brand, String model, Integer price, boolean customs, Double fuelConsumption, Double enginePower,
               Integer race, Integer year, Set<CarAttributeValue> attributeValues, Person person) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.customs = customs;
        this.fuelConsumption = fuelConsumption;
        this.enginePower = enginePower;
        this.race = race;
        this.year = year;
        this.attributeValues = attributeValues;
        this.person = person;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public Set<CarAttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(Set<CarAttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }

    public boolean isInLease() {
        return inLease;
    }

    public void setInLease(boolean inLease) {
        this.inLease = inLease;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Set<CarDeliveryRequest> getRequests() {
        return requests;
    }

    public void setRequests(Set<CarDeliveryRequest> requests) {
        this.requests = requests;
    }
}