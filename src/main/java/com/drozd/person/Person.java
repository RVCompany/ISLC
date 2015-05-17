package com.drozd.person;

import com.drozd.account.Account;
import com.drozd.firm.Firm;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

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

    @Column
    private Integer phoneNumber;

    @Column
    private Integer address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firm_id", nullable = true)
    private Firm firm;

    @OneToOne
    @JoinColumn(
            name = "account_id", unique = true, nullable = false, updatable = false)
    private Account account;

    protected Person() {
    }

    public Person(String firstName, String lastName, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
    }

    public Person(String firstName, String lastName, String passport, Date dateOfBirth, Integer phoneNumber, Integer address, Firm firm, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.firm = firm;
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public Firm getFirm() {
        return this.firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}

