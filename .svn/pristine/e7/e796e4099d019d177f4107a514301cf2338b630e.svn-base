/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugstore.client.util;

import java.io.Serializable;
import javax.xml.datatype.XMLGregorianCalendar;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author ekisa
 */
public class XMLDateAndStringConverter extends Converter<XMLGregorianCalendar, String> implements Serializable{

    public XMLDateAndStringConverter() {
        super();
    }
    
    @Override
    public String convertForward(XMLGregorianCalendar xmlDate) {
        return DrugstoreClientUtil.dateXMLToString(xmlDate);
    }

    @Override
    public XMLGregorianCalendar convertReverse(String arg) {
        return DrugstoreClientUtil.stringToDateXml(arg);
    }

}
