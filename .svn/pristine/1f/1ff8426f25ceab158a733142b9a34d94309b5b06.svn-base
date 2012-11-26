/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugstore.client.ui.renderer;

import com.drugstore.client.ui.prescription.EditPrescriptionForm;
import com.drugstore.client.ws.PrescriptionLineItem;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author ekisa
 */
public class PrescriptionLineItemCellRenderer extends JLabel implements TableCellRenderer {

    public Component getTableCellRendererComponent(
            final JTable table, Object object,
            boolean isSelected, boolean hasFocus, final int row, int column) 
    {
        if(column == 3){
            JButton deleteBtn = new JButton(" - ");
            deleteBtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    final DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.removeRow(row);
                }
            });
            return deleteBtn;
        }
        
        /*
        if(!(object instanceof PrescriptionLineItem)){
            return this;
        }
        PrescriptionLineItem prescriptionLineItem = (PrescriptionLineItem) object;
        switch(column){
            case EditPrescriptionForm.PLI_DRUG_INDEX:
                setText(prescriptionLineItem.getDrug().getName());
                break;
            case EditPrescriptionForm.PLI_QUANTITY_INDEX:
                setText(prescriptionLineItem.getQuantity().toString());
                break;
            case EditPrescriptionForm.PLI_USAGE_INDEX:
                setText(prescriptionLineItem.getUsage().toString());
                break;

        }
        */
        
        return this;
    }
}
