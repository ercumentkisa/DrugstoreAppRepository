/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugstore.client.ui.renderer;

import com.drugstore.client.ws.Drug;
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
public class DrugListRenderer extends JLabel implements ListCellRenderer{

    public DrugListRenderer() {
        super();
    }
        
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(index <= -1 && !(value instanceof Drug)){
            setText("Please select a Prescription Type first");
            return this;
        }
        if(!(value instanceof Drug)){
            return this;
        }
        Drug drug = (Drug) value;
        this.setBackground(Color.WHITE);
        this.setText(" " + drug.getName() + " " + "(" + drug.getCode() + ")");
        
        return this;
    }
    
}
