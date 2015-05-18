package com.drozd.bulider;

import com.drozd.persistence.models.Account;
import com.drozd.persistence.models.Person;

public class PersonHelper {

    public static Person getAdminPerson(Account account){
        return new Person("Admin", "Admin", account);
    }

    public static Person getDefaultUserPerson(Account account){
        return new Person("Default", "User", account);
    }

    public static Account getAdminAccount(){
        return new Account("admin", "admin", "ROLE_ADMIN");
    }

    public static Account getDefaultUserAccount(){
        return new Account("user", "demo", "ROLE_USER");
    }
}
