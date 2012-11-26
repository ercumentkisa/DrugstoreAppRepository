/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugstore.client.ui.renderer;

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
public class PatientListRenderer extends JLabel implements ListCellRenderer{

    public PatientListRenderer() {
        super();
    }
        
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (index <= -1 && !(value instanceof Patient)) {
            setText("Please select a Patient");
            return this;
        }
        if(!(value instanceof Patient)){
            return this;
        }
        Patient patient = (Patient) value;
        this.setBackground(Color.WHITE);
        this.setText(" " + patient.getName() + " " + patient.getSurname() + " (" + patient.getSsn() + ")");
        
        return this;
    }
    
}
