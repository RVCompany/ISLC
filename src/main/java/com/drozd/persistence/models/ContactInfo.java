package com.drozd.persistence.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@SuppressWarnings("serial")
@Entity
@Table(name = "contactInfo")
public class ContactInfo implements java.io.Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contactInfo")
    private Set<Address> addresses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contactInfo")
    private Set<Phone> phones;

    @Column
    private String site;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firmId", nullable = true)
    private Firm firm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId", nullable = true)
    private Person person;

    public ContactInfo() {
    }

    public ContactInfo(Long id, Set<Address> addresses, Set<Phone> phones, String site, Firm firm, Person person) {
        this.id = id;
        this.addresses = addresses;
        this.phones = phones;
        this.site = site;
        this.firm = firm;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}