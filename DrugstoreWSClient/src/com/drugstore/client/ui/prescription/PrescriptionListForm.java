/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PrescriptionListForm.java
 *
 * Created on 22.Ara.2011, 22:06:50
 */
package com.drugstore.client.ui.prescription;

import com.drugstore.client.ui.model.PrescriptionTableModel;
import com.drugstore.client.DrugstoreWSClient;
import com.drugstore.client.ws.Prescription;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.swingbinding.JTableBinding;

/**
 *
 * @author ekisa
 */
public class PrescriptionListForm extends javax.swing.JPanel implements PropertyChangeListener{

    /** Creates new form PrescriptionListForm */
    public PrescriptionListForm() {
        initComponents();        
        initData();
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("prescriptionList")){
            this.initData();
        }
    }

    private void editPrescription() {
        editPrescriptionForm = new EditPrescriptionForm(this, true);
        editPrescriptionForm.addPropertyChangeListener(this);
        PrescriptionTableModel model = (PrescriptionTableModel)table.getModel();
        editPrescriptionForm.editPrescription(model.getRowData(table.getSelectedRow()));
        editPrescriptionForm.setVisible(true);
    }

    private boolean canEditPrescription() {
        if (table.getSelectedRow() == -1) {
            return false;
        }
        return true;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editPrescriptionForm = new com.drugstore.client.ui.prescription.EditPrescriptionForm();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new JTable(new PrescriptionTableModel());
        jPanel1 = new javax.swing.JPanel();
        editButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();

        javax.swing.GroupLayout editPrescriptionFormLayout = new javax.swing.GroupLayout(editPrescriptionForm.getContentPane());
        editPrescriptionForm.getContentPane().setLayout(editPrescriptionFormLayout);
        editPrescriptionFormLayout.setHorizontalGroup(
            editPrescriptionFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        editPrescriptionFormLayout.setVerticalGroup(
            editPrescriptionFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setPreferredSize(new java.awt.Dimension(700, 450));

        table.setColumnSelectionAllowed(true);
        table.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(table);
        table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jPanel1.setPreferredSize(new java.awt.Dimension(700, 150));

        editButton.setText("Edit");
        editButton.setPreferredSize(new java.awt.Dimension(50, 25));
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        newButton.setText("New");
        newButton.setPreferredSize(new java.awt.Dimension(50, 25));
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(339, Short.MAX_VALUE)
                .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(newButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        editPrescriptionForm = new EditPrescriptionForm(this, true);
        editPrescriptionForm.addPropertyChangeListener(this);
        editPrescriptionForm.newPrescription();
        editPrescriptionForm.setVisible(true);
        
    }//GEN-LAST:event_newButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        if (canEditPrescription()) {
            editPrescription();
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        final PrescriptionTableModel model = (PrescriptionTableModel) table.getModel();
        int index = table.getSelectedRow();
        Prescription itemToRemove = model.getRowData(index);
        DrugstoreWSClient.getInstance().getWebService().getDrugstoreWSPort().removePrescription(itemToRemove);
        model.removeData(index);
    }//GEN-LAST:event_deleteBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editButton;
    private com.drugstore.client.ui.prescription.EditPrescriptionForm editPrescriptionForm;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton newButton;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

    private void initData() {
        final PrescriptionTableModel model = (PrescriptionTableModel) table.getModel();
        final List<Prescription> prescriptionList = DrugstoreWSClient.getInstance().getWebService().getDrugstoreWSPort().listPrescriptions();
        model.setData(prescriptionList);
    }
}