package com.drozd.forms;

import com.drozd.persistence.models.Account;
import com.drozd.persistence.models.Person;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;


public class PersonalDataForm {

    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
    private static final String EMAIL_MESSAGE = "{email.message}";

    @NotBlank(message = PersonalDataForm.NOT_BLANK_MESSAGE)
    @Email(message = PersonalDataForm.EMAIL_MESSAGE)
    private String email;

    @NotBlank(message = PersonalDataForm.NOT_BLANK_MESSAGE)
    private String password;

    @NotBlank(message = PersonalDataForm.NOT_BLANK_MESSAGE)
    @Size(min = 2, max = 14)
    private String firstName;

    @NotBlank(message = PersonalDataForm.NOT_BLANK_MESSAGE)
    @Size(min = 2, max = 14)
    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Account createAccount() {
        return new Account(getEmail(), getPassword(), "ROLE_USER");
    }

    public Person createPerson(Account account) {
        return new Person(firstName, lastName, account);
    }
}
