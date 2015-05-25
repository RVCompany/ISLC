package com.drozd.persistence.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
@Entity
@Table(name = "carAttributeValue")
public class CarAttributeValue implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "carAttributeValueId", unique = true, nullable = false)
    private Long carAttributeValueId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carAttributeId", nullable = true)
    private CarAttribute carAttribute;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "car_carAttributeValue", catalog = "islc", joinColumns = {
            @JoinColumn(name = "carAttributeValueId", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "carId",
                    nullable = false, updatable = false) })
    private Set<Car> cars = new HashSet<>(0);

    @Column
    private String attributeValue;

    public CarAttributeValue() {
    }

    public Long getCarAttributeValueId() {
        return carAttributeValueId;
    }

    public void setCarAttributeValueId(Long carAttributeValueId) {
        this.carAttributeValueId = carAttributeValueId;
    }

    public CarAttribute getCarAttribute() {
        return carAttribute;
    }

    public void setCarAttribute(CarAttribute carAttribute) {
        this.carAttribute = carAttribute;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
}