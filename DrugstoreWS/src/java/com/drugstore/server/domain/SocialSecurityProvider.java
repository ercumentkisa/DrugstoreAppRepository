package com.drugstore.server.domain;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 13.12.2011
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
public class SocialSecurityProvider extends Entity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
