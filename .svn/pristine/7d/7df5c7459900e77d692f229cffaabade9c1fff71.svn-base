/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugstore.client.ui.renderer;

import com.drugstore.client.ws.Doctor;
import com.drugstore.client.ws.Manufacturer;
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
public class ManufacturerListRenderer extends JLabel implements ListCellRenderer{

    public ManufacturerListRenderer() {
        super();
    }
        
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (index <= -1 && !(value instanceof Manufacturer)) {
            setText("Please select a Manufacturer");
            return this;
        }
        if(!(value instanceof Manufacturer)){
            return this;
        }
        Manufacturer manufacturer = (Manufacturer) value;
        this.setBackground(Color.WHITE);
        this.setText(" " + manufacturer.getName());
        
        return this;
    }
    
}
