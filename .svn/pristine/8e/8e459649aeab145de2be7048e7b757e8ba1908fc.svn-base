/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugstore.client.ui.model;

import com.drugstore.client.ws.Patient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 *
 * @author ekisa
 */
public class PatientComboBoxModel extends DefaultComboBoxModel{

    public PatientComboBoxModel() {
    }
    
    public void setDataList(List<Patient> dataList) {
        this.removeAllElements();
        for(Iterator<Patient> iterator = dataList.iterator(); iterator.hasNext();){
            addElement(iterator.next());
        }
    }
    
   
}
