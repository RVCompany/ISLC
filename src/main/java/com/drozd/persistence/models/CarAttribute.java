package com.drozd.persistence.models;

import javax.persistence.*;
import java.util.Set;

@SuppressWarnings("serial")
@Entity
@Table(name = "carAttribute")
public class CarAttribute implements java.io.Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String attributeName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "carAttribute")
    private Set<CarAttributeValue> values;

    public CarAttribute() {
    }

    public CarAttribute(String attributeName) {
        this.attributeName = attributeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public Set<CarAttributeValue> getValues() {
        return values;
    }

    public void setValues(Set<CarAttributeValue> values) {
        this.values = values;
    }
}