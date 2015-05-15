package com.drozd.person;

import com.drozd.account.Account;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

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
}

