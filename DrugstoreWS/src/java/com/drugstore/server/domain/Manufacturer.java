package com.drugstore.server.domain;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 30.10.2011
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 */
public class Manufacturer extends Entity{
    @NotNull
    private String name;
    @NotNull
    private String country;
    @NotNull
    private String contact;
    @NotNull
    @Digits(integer = 10, fraction = 0)
    private String contactPhone;

    // RequestFactory icin no-arg constructor gerekli
    public Manufacturer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    
}
