/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugstore.client.ui.renderer;

import com.drugstore.client.ws.Patient;
import com.drugstore.client.ws.PrescriptionType;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author ekisa
 */
public class PrescriptionTypeListRenderer extends JLabel implements ListCellRenderer{

    public PrescriptionTypeListRenderer() {
        super();
    }
        
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (index <= -1 && !(value instanceof PrescriptionType)) {
            setText("Please select a Prescription Type");
            return this;
        }
        if(!(value instanceof PrescriptionType)){
            return this;
        }
        PrescriptionType prescriptionType = (PrescriptionType) value;
        this.setBackground(Color.WHITE);
        this.setText(" " + prescriptionType.getName() + " ");
        
        return this;
    }
    
}
