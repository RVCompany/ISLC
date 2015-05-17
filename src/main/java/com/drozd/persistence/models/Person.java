package com.drozd.persistence.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@SuppressWarnings("serial")
@Entity
@Table(name = "person")
@NamedQuery(name = Person.FIND_BY_ACCOUNT, query = "select p from Person p where p.account = :account")
public class Person implements java.io.Serializable {

    public static final String FIND_BY_ACCOUNT = "Person.findByAccount";

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String passport;

    @Column
    private Date dateOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firmId", nullable = true)
    private Firm firm;

    @OneToOne
    @JoinColumn(name = "accountId", unique = true, nullable = false)
    private Account account;

    @OneToOne
    @JoinColumn(name = "contactInfoId")
    private ContactInfo contactInfo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private Set<Car> cars;

    public Person() {
    }

    public Person(String firstName, String lastName, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
    }

    public Person(String firstName, String lastName, Account account, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}

