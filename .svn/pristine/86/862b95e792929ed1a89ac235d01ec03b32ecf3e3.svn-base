/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugstore.client.aspect;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ekisa
 */
public class EventFiringAbstractEntity {

    @XmlTransient
    protected PropertyChangeSupport props = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener l) {
        System.out.println("addPropertyChangeListener");
        props.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        System.out.println("removePropertyChangeListener");
        props.removePropertyChangeListener(l);
    }

    public String getPropertyName(String setterMethodName) {
        setterMethodName = setterMethodName.substring(3);
        String firstLetter = setterMethodName.substring(0, 1);
        setterMethodName = firstLetter.toLowerCase() + setterMethodName.substring(1);
        return setterMethodName;
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return props;
    }

}
