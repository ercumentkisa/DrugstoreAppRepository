/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugstore.client.util;

import java.io.Serializable;
import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author ekisa
 */
public class XMLDateAndUtilDateConverter extends Converter<XMLGregorianCalendar, Date> implements Serializable{

    public XMLDateAndUtilDateConverter() {
        super();
    }
    
    @Override
    public Date convertForward(XMLGregorianCalendar xmlDate) {
        return xmlDate.toGregorianCalendar().getTime();
    }

    @Override
    public XMLGregorianCalendar convertReverse(Date arg) {
        return DrugstoreClientUtil.dateUtiltoDateXML(arg);
    }

}
