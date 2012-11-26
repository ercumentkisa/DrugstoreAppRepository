/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugstore.client.ui.renderer;

import com.drugstore.client.ws.Doctor;
import com.drugstore.client.ws.Patient;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author ekisa
 */
public class DoctorListRenderer extends JLabel implements ListCellRenderer{

    public DoctorListRenderer() {
        super();
    }
        
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (index <= -1 && !(value instanceof Doctor)) {
            setText("Please select a Doctor");
            return this;
        }
        if(!(value instanceof Doctor)){
            return this;
        }
        Doctor doctor = (Doctor) value;
        this.setBackground(Color.WHITE);
        this.setText(" " + doctor.getName() + " " + doctor.getSurname() + " (" + doctor.getExpertise() + ")");
        
        return this;
    }
    
}
