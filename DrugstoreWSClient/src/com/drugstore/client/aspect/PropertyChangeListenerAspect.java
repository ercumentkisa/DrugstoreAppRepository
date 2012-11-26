/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugstore.client.aspect;

import com.drugstore.client.ws.Entity;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 *
 * @author ekisa
 */
public @Aspect
class PropertyChangeListenerAspect {

    @Around("execution(void *..*.set*(..))")
    public void interceptSetterAndFirePropertyChange(ProceedingJoinPoint pjp) throws Throwable {
        
        String setterMethodName = pjp.getSignature().getName().substring(3);
        if(setterMethodName.length() <=3){
            pjp.proceed();
        }
        else{
            String firstLetter = setterMethodName.substring(0, 1);
            String propertyName = firstLetter.toLowerCase() + setterMethodName.substring(1);
            Object oldValue = null;
            try {
                oldValue = PropertyUtils.getProperty(pjp.getTarget(), propertyName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Object newValue = pjp.getArgs()[0];

            pjp.proceed();

            System.out.println("Firing event : " + propertyName + "; oldValue = " + oldValue + "; newValue = " + newValue);
            PropertyChangeEvent pe = new PropertyChangeEvent(pjp.getTarget(), propertyName, oldValue, newValue);
            ((EventFiringAbstractEntity) pjp.getTarget()).getPropertyChangeSupport().firePropertyChange(pe);
            
        }
        
    }
}
