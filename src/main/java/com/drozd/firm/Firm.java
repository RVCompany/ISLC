package com.drozd.firm;

import javax.persistence.*;


import com.drozd.account.Account;
import com.drozd.person.Person;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Roman on 17.05.2015.
 */



@SuppressWarnings("serial")
@Entity
@Table(name = "firm")

public class Firm implements java.io.Serializable {


    public static final String FIND_BY_ACCOUNT = "Person.findByAccount";

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String shortName;

    @Column
    private String EDRPOU_Code;

    @Column
    private Integer address;

    @Column
    private Date dateOfReestr;

    @Column
    private Integer phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "persons")
    private Set<Person> persons;

    @Column
    private String site;


    public Set<Person> getPersons() {
        return this.persons;
    }

    public Firm(String name, String site, Integer phoneNumber, Date dateOfReestr, Integer address, String EDRPOU_Code, String shortName) {
        this.name = name;
        this.site = site;
        this.phoneNumber = phoneNumber;
        this.dateOfReestr = dateOfReestr;
        this.address = address;
        this.EDRPOU_Code = EDRPOU_Code;
        this.shortName = shortName;
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

    public String getEDRPOU_Code() {
        return EDRPOU_Code;
    }

    public void setEDRPOU_Code(String EDRPOU_Code) {
        this.EDRPOU_Code = EDRPOU_Code;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public Date getDateOfReestr() {
        return dateOfReestr;
    }

    public void setDateOfReestr(Date dateOfReestr) {
        this.dateOfReestr = dateOfReestr;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}