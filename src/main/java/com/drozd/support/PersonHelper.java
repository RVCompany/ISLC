package com.drozd.support;

import com.drozd.persistence.models.Account;
import com.drozd.persistence.models.Person;

public class PersonHelper {

    public final static String ADMIN_EMAIL = "admin@admin.com";

    public final static String DEMO_USER_EMAIL = "user@user.com";

    public static Person getAdminPerson(Account account){
        return new Person("Admin", "Admin", account);
    }

    public static Person getDefaultUserPerson(Account account){
        return new Person("Default", "User", account);
    }

    public static Account getAdminAccount(){
        return new Account(ADMIN_EMAIL, "admin", "ROLE_ADMIN");
    }

    public static Account getDefaultUserAccount(){
        return new Account(DEMO_USER_EMAIL, "demo", "ROLE_USER");
    }
}
