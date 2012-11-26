package com.drugstore.server.domain;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 13.12.2011
 * Time: 20:50
 * To change this template use File | Settings | File Templates.
 */
public class Patient extends Entity{
    private String name;
    private String surname;
    private String ssn;
    private String address;
    private String homePhone;
    private String gsm;
    private String email;
    private SocialSecurityProvider socialSecurityProvider;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SocialSecurityProvider getSocialSecurityProvider() {
        return socialSecurityProvider;
    }

    public void setSocialSecurityProvider(SocialSecurityProvider socialSecurityProvider) {
        this.socialSecurityProvider = socialSecurityProvider;
    }
}
