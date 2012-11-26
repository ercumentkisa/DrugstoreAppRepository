/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugstore.client.ui.renderer;

import com.drugstore.client.ws.Doctor;
import com.drugstore.client.ws.Patient;
import com.drugstore.client.ws.SocialSecurityProvider;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author ekisa
 */
public class SSPListRenderer extends JLabel implements ListCellRenderer{

    public SSPListRenderer() {
        super();
    }
        
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (index <= -1 && !(value instanceof SocialSecurityProvider)) {
            setText("Please select a Social Security Provider");
            return this;
        }
        if(!(value instanceof SocialSecurityProvider)){
            return this;
        }
        SocialSecurityProvider socialSecurityProvider = (SocialSecurityProvider) value;
        this.setBackground(Color.WHITE);
        this.setText(" " + socialSecurityProvider.getName() + " ");
        
        return this;
    }
    
}
