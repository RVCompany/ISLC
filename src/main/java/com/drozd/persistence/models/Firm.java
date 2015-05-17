package com.drozd.persistence.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@SuppressWarnings("serial")
@Entity
@Table(name = "firm")
public class Firm implements java.io.Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String shortName;

    @Column
    private String eDrpouCode;

    @OneToOne
    @JoinColumn(name = "contactInfoId")
    private ContactInfo contactInfo;

    @Column
    private Date dateOfRegistration;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "firm")
    private Set<Person> persons;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "firm")
    private Set<Car> cars;

    public Firm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String geteDrpouCode() {
        return eDrpouCode;
    }

    public void seteDrpouCode(String eDrpouCode) {
        this.eDrpouCode = eDrpouCode;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}