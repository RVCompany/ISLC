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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId", nullable = true)
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firmId", nullable = true)
    private Firm firm;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carAttribute")
    private Set<CarAttributeValue> values;

    public CarAttribute() {
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

    public Set<CarAttributeValue> getValues() {
        return values;
    }

    public void setValues(Set<CarAttributeValue> values) {
        this.values = values;
    }
}