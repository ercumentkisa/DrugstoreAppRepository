/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugstore.client.util;

import com.drugstore.client.exception.DrugStoreRuntimeException;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JDialog;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author ekisa
 */
public final class DrugstoreClientUtil {
    
    public static String dateUtilToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
    
    public static Date stringToDateUtil(String date)  {
        try{
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.parse(date);
        }catch(Throwable t){
            throw new DrugStoreRuntimeException("Date parse hatasi", t);
        }
    }
    
    public static String dateXMLToString(XMLGregorianCalendar date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date.toGregorianCalendar().getTime());
    }
    
    public static XMLGregorianCalendar dateUtiltoDateXML(Date date){
        try {
            GregorianCalendar gcal = new GregorianCalendar();
            gcal.setTime(date);
            XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
            return xgcal;
        }catch(Throwable t){
            throw new DrugStoreRuntimeException("Date parse hatasi", t);
        }
        
    }
    
    public static XMLGregorianCalendar stringToDateXml(String string) {
        try {
            Date utilDate = stringToDateUtil(string);
            return dateUtiltoDateXML(utilDate);
        } catch (Throwable t) {
            throw new DrugStoreRuntimeException("Date parse hatasi", t);
        }
    }
    
    public static Frame getTopFrame() {
        Frame[] frames = Frame.getFrames();
        for (int i = 0; i < frames.length; i++) {
            if (frames[i].getFocusOwner() != null) {
                return frames[i];
            }
        }
        if (frames.length > 0) {
            return frames[0];
        }
        return null;
    }
    
    
    public static void centerComponent(Component component) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        component.setSize(screenWidth / 2, screenHeight / 2);
        component.setLocation(screenWidth / 4, screenHeight / 8);
    }

    public static void centerComponent(JDialog component) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        component.setSize(screenWidth / 2, screenHeight / 2 + 100);
        component.setLocation(screenWidth / 4, screenHeight / 8);
    }
    
    public static boolean isNumber(Object o){
        if(o == null){
            return false;
        }
        else if (o instanceof Number) {
            return true;
        }
        else if(o instanceof String){
            try{
                Long.valueOf(""+o);
                return true;
            }catch(Throwable t){
                return false;
            }
        }
        
        return false;
    }
}
