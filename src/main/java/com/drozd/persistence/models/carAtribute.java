package com.drozd.persistence.models;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "leaseSubject")
public class carAtribute implements java.io.Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String atributeName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId", nullable = true)
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firmId", nullable = true)
    private Firm firm;

    public carAtribute() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBodyType() {
        return atributeName;
    }

    public void setBodyType(String bodyType) {
        this.atributeName = atributeName;
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
}